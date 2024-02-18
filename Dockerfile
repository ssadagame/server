FROM gradle:7.6.3-jdk11-alpine AS build

ARG APP_NAME
ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY $APP_NAME/build.gradle.kts .$APP_NAME/
COPY gradle ./gradle/
RUN ./gradlew ${APP_NAME}:build -x test -x runKtlintCheckOverTestSourceSet -x runKtlintCheckOverMainSourceSet -x copyPreCommit 2>/dev/null || true

COPY . .
RUN ./gradlew ${APP_NAME}:build -x test -x runKtlintCheckOverTestSourceSet -x runKtlintCheckOverMainSourceSet -x copyPreCommit


FROM openjdk:11 AS run

ARG APP_NAME
ENV APP_HOME=/app
ENV JAR_FILE=${APP_NAME}-0.0.1-SNAPSHOT.jar
COPY --from=build ${APP_HOME}/${APP_NAME}/build/libs/${JAR_FILE} .

ENTRYPOINT java \
  -Dspring.profiles.active=${APP_PHASE:-dev} \
  -Dsun.net.inetaddr.ttl=0 \
  -XX:+TieredCompilation \
  -XX:+UseNUMA \
  -XX:+UseG1GC \
  -XX:MaxGCPauseMillis=200 \
  -XX:CompileThreshold=200 \
  -XX:InitiatingHeapOccupancyPercent=10 \
  -XX:+UseLargePagesInMetaspace \
  -XX:+AlwaysPreTouch \
  -XX:+ParallelRefProcEnabled \
  -XX:+UseCompressedOops \
  -XX:-OmitStackTraceInFastThrow \
  --add-opens=java.base/java.lang.invoke=ALL-UNNAMED \
  -jar ${JAR_FILE}
