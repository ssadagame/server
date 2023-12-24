package com.sincheon.ssadagame.interfaces.controller

import com.sincheon.ssadagame.application.service.GameService
import com.sincheon.ssadagame.domain.game.model.Game
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/game")
@Tag(name = "홈", description = "홈 화면 정보 조회 API")
class GameController(
    private val gameService: GameService,
) {
    @GetMapping("/popular")
    @Operation(summary = "인기게임 조회")
    fun getTopSellingGames(
        @RequestParam(defaultValue = "1") page: Int = 1,
        @RequestParam(defaultValue = "60") size: Int = 60,
    ): GamePageResponse {
        val gamePage = gameService.getTopSellingGames(page, size)
        return GamePageResponse(
            games = gamePage.games,
            size = gamePage.size,
            page = gamePage.page,
            lastPage = gamePage.lastPage,
            total = gamePage.total,
        )
    }

    data class GamePageResponse(
        val games: List<Game>,
        val size: Int,
        val page: Int,
        val lastPage: Int?,
        val total: Int?,
    )

    @GetMapping("/banner")
    @Operation(summary = "홈 배너 조회")
    fun getBanner() {
    }
}
