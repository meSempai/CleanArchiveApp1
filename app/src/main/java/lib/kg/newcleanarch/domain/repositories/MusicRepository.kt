package lib.kg.newcleanarch.domain.repositories

import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MusicRepository {

    suspend fun addMusic(music: Music)

    fun getMusics(): Flow<Resource<List<Music>>>

    fun getMusicsByPerfomer(): Flow<Resource<List<Music>>>

    fun getMusicsByDuration(): Flow<Resource<List<Music>>>

    suspend fun updateMusic(music: Music)

    suspend fun deleteMusic(music: Music)
}