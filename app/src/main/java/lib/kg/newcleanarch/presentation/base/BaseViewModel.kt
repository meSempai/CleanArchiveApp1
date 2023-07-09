package lib.kg.newcleanarch.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import lib.kg.newcleanarch.domain.utils.Resource
import lib.kg.newcleanarch.presentation.utils.UiState

abstract class BaseViewModel: ViewModel() {

    protected fun<T> Flow<Resource<T>>.collectData(
        state: MutableStateFlow<UiState<T>>
    ) {
        viewModelScope.launch {
            this@collectData.collect {
                when (it) {
                    is Resource.Loading -> {
                        state.value = UiState.Loading()
                    }

                    is Resource.Success -> {
                        if (it.data != null) {
                            state.value = UiState.Success(it.data)
                        }
                    }

                    is Resource.Error -> {
                        state.value = UiState.Error(it.message ?: "Error")
                    }
                }
            }
        }
    }

}