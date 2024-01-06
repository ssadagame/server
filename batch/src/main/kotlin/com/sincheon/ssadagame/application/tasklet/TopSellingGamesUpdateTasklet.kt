package com.sincheon.ssadagame.application.tasklet

import com.sincheon.ssadagame.application.service.GreenmanGamingService
import com.sincheon.ssadagame.application.service.SteamService
import com.sincheon.ssadagame.domain.game.model.GamePage
import com.sincheon.ssadagame.domain.game.service.TopSellingGames
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class TopSellingGamesUpdateTasklet(
    private val steamService: SteamService,
    private val greenmanGamingService: GreenmanGamingService,
    private val topSellingGames: TopSellingGames,
) : Tasklet {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        logger.info("TopSellingGamesUpdateTasklet execute started")
        updateTopSellingGames()
        logger.info("TopSellingGamesUpdateTasklet execute finished")
        return RepeatStatus.FINISHED
    }

    private fun updateTopSellingGames() {
        for (page in 1..MAX_PAGE) {
            topSellingGames.set(
                getTopSellingGames(page, PAGE_SIZE),
                page,
                PAGE_SIZE,
            )
        }
    }

    private fun getTopSellingGames(page: Int, size: Int): GamePage {
        val gamePage = steamService.getTopSellingGames(page, size)
        gamePage.games.forEach { game ->
            greenmanGamingService.getGamePrice(game.name)?.let { price ->
                game.priceInfo.add(price)
            }
        }
        return gamePage
    }

    companion object {
        const val PAGE_SIZE = 60
        const val MAX_PAGE = 5
    }
}
