package com.sincheon.ssadagame.domain.game.model

import javax.persistence.AttributeConverter
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(
    name = "game_detail",
    indexes = [
        Index(name = "idx_app_id", columnList = "appId", unique = true),
        Index(name = "idx_name", columnList = "name", unique = false),
    ]
)
class GameDetailEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val appId: Long,
    val name: String,
    val type: Type,
    val headerImage: String,
    val capsuleImage: String,
    @Convert(converter = StringListConverter::class)
    val thumbnailImages: List<String>,
    @Convert(converter = StringListConverter::class)
    val originalImages: List<String>,
    @OneToMany(mappedBy = "gameDetail")
    val genres: Set<GameDetailGenreEntity>,
) {
    enum class Type {
        GAME,
        DLC,
        ;
    }

    class StringListConverter : AttributeConverter<List<String>, String> {
        override fun convertToDatabaseColumn(attribute: List<String>): String {
            return attribute.joinToString(",")
        }

        override fun convertToEntityAttribute(dbData: String): List<String> {
            return dbData.split(",")
        }
    }
}
