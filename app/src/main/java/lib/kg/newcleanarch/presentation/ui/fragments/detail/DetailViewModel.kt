package lib.kg.newcleanarch.presentation.ui.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.domain.usecases.DeleteMusicUseCase
import lib.kg.newcleanarch.domain.usecases.UpdateMusicUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val updateMusicUseCase: UpdateMusicUseCase,
    private val deleteMusicUseCase: DeleteMusicUseCase
) : ViewModel() {

    suspend fun updateMusic(music: Music) {
        viewModelScope.launch(Dispatchers.IO) {
            updateMusicUseCase.execute(music)
        }
    }

    suspend fun deleteMusic(music: Music) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMusicUseCase.execute(music)
        }
    }
}