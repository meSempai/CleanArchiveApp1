package lib.kg.newcleanarch.presentation.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lib.kg.newcleanarch.presentation.utils.UiState

abstract class BaseFragment: Fragment() {

    protected fun<T> StateFlow<UiState<T>>.collectState(
        loadingState: (UiState<T>) -> Unit,
        successState: (data: T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            this@collectState.collect {
                when (it) {
                    is UiState.EmptyState -> {
                    }

                    is UiState.Error -> {
                        Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }

                    is UiState.Loading -> {
                        loadingState.invoke(UiState.Loading())
                    }

                    is UiState.Success -> {
                        successState.invoke(it.data)
                    }
                }
            }
        }
    }

}