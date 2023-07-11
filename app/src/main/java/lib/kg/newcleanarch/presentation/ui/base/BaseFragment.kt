package lib.kg.newcleanarch.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lib.kg.newcleanarch.presentation.utils.UiState

abstract class BaseFragment<VB: ViewBinding>: Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiBox()
    }

    abstract fun uiBox()

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

}