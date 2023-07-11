package lib.kg.newcleanarch.data.repositories

import lib.kg.newcleanarch.data.local.MusicDao
import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.domain.repositories.MusicRepository
import lib.kg.newcleanarch.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import lib.kg.newcleanarch.data.base.BaseRepository
import lib.kg.newcleanarch.data.mappers.toEntity
import lib.kg.newcleanarch.data.mappers.toMusic
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val musicDao: MusicDao) : BaseRepository(),
    MusicRepository {
    override suspend fun addMusic(music: Music) {
        musicDao.addMusic(music.toEntity())
    }

    override suspend fun updateMusic(music: Music){
        musicDao.updateMusic(music.toEntity())    }

    override suspend fun deleteMusic(music: Music){
        musicDao.deleteMusic(music.toEntity())
    }

    override fun getMusics(): Flow<Resource<List<Music>>> = doRequest { musicDao.getMusics().map { it.toMusic() } }

    override fun getMusicsByPerfomer(): Flow<Resource<List<Music>>>  = doRequest { musicDao.getMusicByPerfomer().map { it.toMusic() } }

    override fun getMusicsByDuration(): Flow<Resource<List<Music>>> = doRequest { musicDao.getMusicByDuration().map { it.toMusic() } }
}