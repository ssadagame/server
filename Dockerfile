FROM gradle:7.4.2-jdk11-alpine AS build

WORKDIR /app
COPY . /app

ARG APP_NAME
RUN ./gradlew ${APP_NAME}:build -x test -x runKtlintCheckOverTestSourceSet -x runKtlintCheckOverMainSourceSet


FROM openjdk:11 AS run

WORKDIR /app
ARG APP_NAME

COPY --from=build /app/${APP_NAME}/build/libs/${APP_NAME}-0.0.1-SHAPSHOT.jar /app/app.jar

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
  -jar /app/app.jar
