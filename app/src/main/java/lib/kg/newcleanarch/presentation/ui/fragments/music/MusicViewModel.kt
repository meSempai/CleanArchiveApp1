package lib.kg.newcleanarch.presentation.ui.fragments.music

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.domain.usecases.AddMusicUseCase
import lib.kg.newcleanarch.domain.usecases.GetMusicUseCase
import lib.kg.newcleanarch.presentation.base.BaseViewModel
import lib.kg.newcleanarch.presentation.utils.UiState
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getMusicUseCase: GetMusicUseCase,
    private val addMusicUseCase: AddMusicUseCase,
) : BaseViewModel() {

    private val _getAllMusicState = MutableStateFlow<UiState<List<Music>>>(UiState.EmptyState())
    val getAllMusicStates = _getAllMusicState.asStateFlow()

    fun addMusic(music: Music){
        viewModelScope.launch(Dispatchers.IO) {
            addMusicUseCase.execute(music)
        }
    }

    fun getAllMusic() = getMusicUseCase.execute().collectData(_getAllMusicState)
}