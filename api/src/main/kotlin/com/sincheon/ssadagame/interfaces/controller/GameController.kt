package com.sincheon.ssadagame.interfaces.controller

import com.sincheon.ssadagame.application.service.GameService
import com.sincheon.ssadagame.interfaces.controller.response.GameDetailResponse
import com.sincheon.ssadagame.interfaces.controller.response.GamePageResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/game")
@Tag(name = "게임", description = "게임 API")
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

    @GetMapping("/{appId}")
    @Operation(summary = "게임 정보 조회")
    fun getGamePrice(
        @PathVariable appId: Long,
    ): GameDetailResponse {
        val gameDetail = gameService.getGameDetailByAppId(appId)
        return GameDetailResponse(
            appId = gameDetail.appId,
            name = gameDetail.koreanName,
            type = gameDetail.type,
            headerImage = gameDetail.headerImage,
            capsuleImage = gameDetail.capsuleImage,
            screenshots = gameDetail.screenshots,
            genres = gameDetail.genres,
        )
    }

    @GetMapping("/banner")
    @Operation(summary = "홈 배너 조회")
    fun getBanner() {
    }
}
