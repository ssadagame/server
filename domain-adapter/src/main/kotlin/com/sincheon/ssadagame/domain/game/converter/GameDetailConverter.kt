package com.sincheon.ssadagame.domain.game.converter

import com.sincheon.ssadagame.domain.game.model.GameDetail
import com.sincheon.ssadagame.domain.game.model.GameDetailEntity
import com.sincheon.ssadagame.domain.game.model.Genre
import com.sincheon.ssadagame.domain.game.model.GenreEntity
import com.sincheon.ssadagame.domain.game.model.Screenshot

fun GameDetailEntity.toDto() = GameDetail(
    id = id!!,
    appId = appId,
    englishName = englishName,
    koreanName = koreanName,
    type = GameDetail.Type.valueOf(type.name),
    headerImage = headerImage,
    capsuleImage = capsuleImage,
    screenshots = thumbnailImages.zip(originalImages).map { Screenshot(it.first, it.second) },
    genres = genres.map { it.genre.toDto() },
)

fun GameDetail.toEntity() = GameDetailEntity(
    id = id,
    appId = appId,
    englishName = englishName!!,
    koreanName = koreanName,
    type = GameDetailEntity.Type.valueOf(type.name),
    headerImage = headerImage,
    capsuleImage = capsuleImage,
    thumbnailImages = screenshots.map { it.thumbnail },
    originalImages = screenshots.map { it.original },
    genres = emptySet(),
)

fun GenreEntity.toDto() = Genre(
    id = id!!,
    name = name,
)

fun Genre.toEntity() = GenreEntity(
    id = id,
    name = name,
    gameDetails = emptySet(),
)
