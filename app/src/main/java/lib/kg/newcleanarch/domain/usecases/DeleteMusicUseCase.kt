package lib.kg.newcleanarch.domain.usecases

import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.domain.repositories.MusicRepository
import javax.inject.Inject

class DeleteMusicUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend fun execute(music: Music) {
        musicRepository.deleteMusic(music = music)
    }
}