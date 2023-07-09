package lib.kg.newcleanarch.domain.usecases

import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.domain.repositories.MusicRepository
import lib.kg.newcleanarch.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMusicUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    fun execute(): Flow<Resource<List<Music>>> {
        return musicRepository.getMusics()
    }
}