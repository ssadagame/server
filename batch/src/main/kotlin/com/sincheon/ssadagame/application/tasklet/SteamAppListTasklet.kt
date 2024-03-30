package com.sincheon.ssadagame.application.tasklet

import com.sincheon.ssadagame.application.service.SteamService
import com.sincheon.ssadagame.domain.game.repository.GameDetailRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component
import java.lang.Thread.sleep

@Component
class SteamAppListTasklet(
    private val steamService: SteamService,
    private val gameDetailRepository: GameDetailRepository,
) : Tasklet {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        logger.info("SteamAppListTasklet execute started")
        updateSteamAppList()
        logger.info("SteamAppListTasklet execute finished")
        return RepeatStatus.FINISHED
    }

    private fun updateSteamAppList() {
        val apps = steamService.getGameList().appList.apps
        logger.info("Steam App List Size: ${apps.size}")

        apps.forEach { app ->
            if (!gameDetailRepository.existsByAppId(app.appId)) {
                logger.info("Steam App Id: ${app.appId}")
                runCatching {
                    steamService.getGameDetail(app.appId)
                }.onSuccess {
                    it?.copy(
                        id = gameDetailRepository.findByAppId(it.appId)?.id ?: it.id,
                        englishName = app.name
                    )?.also { gameDetail ->
                        if (gameDetail.id == null) {
                            logger.info("save gameDetail: $gameDetail")
                        } else {
                            logger.info("update gameDetail: $gameDetail")
                        }
                        gameDetailRepository.save(gameDetail)
                    }
                    logger.info("Success Steam App Id: ${app.appId}")
                }.onFailure {
                    logger.error("Failure Steam App Id: ${app.appId}")
                }
                sleep(2000)
            }
        }
    }
}
