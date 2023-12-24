package com.sincheon.ssadagame.application.tasklet

import com.sincheon.ssadagame.application.service.GreenmanGamingService
import com.sincheon.ssadagame.application.service.SteamService
import com.sincheon.ssadagame.domain.game.model.GamePage
import com.sincheon.ssadagame.domain.game.service.TopSellingGames
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
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        updateTopGamePrice()
        return RepeatStatus.FINISHED
    }

    private fun updateTopGamePrice() {
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
        const val PAGE_SIZE = 100
        const val MAX_PAGE = 10
    }
}
