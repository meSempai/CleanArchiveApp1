package lib.kg.newcleanarch.data.mappers

import lib.kg.newcleanarch.data.models.MusicEntity
import lib.kg.newcleanarch.domain.models.Music

fun Music.toEntity() : MusicEntity {
    return MusicEntity(id, name, perfomer, duration)
}

fun MusicEntity.toMusic() : Music {
    return Music(id, name, perfomer = perfomer, duration = duration)
}