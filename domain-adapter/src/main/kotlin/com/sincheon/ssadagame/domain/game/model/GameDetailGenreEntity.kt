package com.sincheon.ssadagame.domain.game.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Entity
@Table(
    name = "game_detail_genre",
)
class GameDetailGenreEntity(
    @EmbeddedId
    val id: GameDetailGenreKey,
    @ManyToOne
    @MapsId("gameDetailId")
    @JoinColumn(name = "game_detail_id")
    val gameDetail: GameDetailEntity,
    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genre_id")
    val genre: GenreEntity,
)

@Embeddable
data class GameDetailGenreKey(
    @Column(name = "game_detail_id")
    var gameDetailId: Long,
    @Column(name = "genre_id")
    var genreId: Long,
) : Serializable
