package com.sincheon.ssadagame.domain.game.repository

import com.sincheon.ssadagame.domain.game.converter.toDto
import com.sincheon.ssadagame.domain.game.converter.toEntity
import com.sincheon.ssadagame.domain.game.model.GameDetail
import com.sincheon.ssadagame.domain.game.model.GameDetailGenreEntity
import com.sincheon.ssadagame.domain.game.model.GameDetailGenreKey
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class GameDetailRepositoryImpl(
    private val gameDetailJpaRepository: GameDetailJpaRepository,
    private val genreJpaRepository: GenreJpaRepository,
    private val gameDetailGenreJpaRepository: GameDetailGenreJpaRepository,
) : GameDetailRepository {
    override fun findByAppId(appId: Long) = gameDetailJpaRepository.findByAppId(appId)?.toDto()

    override fun existsByAppId(appId: Long) = gameDetailJpaRepository.existsByAppId(appId)

    override fun save(gameDetail: GameDetail): GameDetail {
        val gameDetailEntity = gameDetailJpaRepository.save(gameDetail.toEntity())
        gameDetail.genres.forEach { genre ->
            val genreEntity = genreJpaRepository.findByName(genre.name) ?: genreJpaRepository.save(genre.toEntity())
            gameDetailGenreJpaRepository.findByIdOrNull(GameDetailGenreKey(gameDetailEntity.id!!, genre.id))
                ?: gameDetailGenreJpaRepository.save(
                    GameDetailGenreEntity(
                        id = GameDetailGenreKey(gameDetailEntity.id!!, genre.id),
                        gameDetail = gameDetailEntity,
                        genre = genreEntity,
                    )
                )
        }
        return gameDetailEntity.toDto()
    }
}
