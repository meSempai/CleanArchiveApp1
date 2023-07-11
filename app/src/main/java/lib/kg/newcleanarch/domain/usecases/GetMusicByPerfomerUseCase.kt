package lib.kg.newcleanarch.domain.usecases

import kotlinx.coroutines.flow.Flow
import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.domain.repositories.MusicRepository
import lib.kg.newcleanarch.domain.utils.Resource
import javax.inject.Inject

class GetMusicByPerfomerUseCase @Inject constructor(private val musicRepository: MusicRepository){

    fun execute(): Flow<Resource<List<Music>>> = musicRepository.getMusicsByPerfomer()

}