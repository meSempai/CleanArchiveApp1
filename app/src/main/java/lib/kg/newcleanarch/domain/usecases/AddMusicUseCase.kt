package lib.kg.newcleanarch.domain.usecases

import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.domain.repositories.MusicRepository
import javax.inject.Inject

class AddMusicUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend fun execute(music: Music) {
        musicRepository.addMusic(music = music)
    }
}