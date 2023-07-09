package lib.kg.newcleanarch.presentation.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import lib.kg.newcleanarch.databinding.FragmentDetailBinding
import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.presentation.ui.fragments.music.MusicFragment.Companion.KEY_MUSIC

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFamily()
        initClickListeners()
    }

    private fun getFamily() {
        with(binding){
            val music = (arguments?.getSerializable(KEY_MUSIC)) as Music
            etName.setText(music.name)
            etPerfomer.setText(music.perfomer)
            etDuration.setText(music.duration)
        }
    }

    private fun initClickListeners() {
        with(binding) {
            val music = (arguments?.getSerializable(KEY_MUSIC)) as Music
            btnUpdate.setOnClickListener {
                viewModel.viewModelScope.launch {
                    viewModel.updateMusic(
                        Music(
                            id = music.id,
                            name = binding.etName.text.toString(),
                            perfomer = binding.etPerfomer.text.toString(),
                            duration = binding.etDuration.text.toString()
                        )
                    )
                }
                findNavController().navigateUp()
            }
            btnDelete.setOnClickListener {
                viewModel.viewModelScope.launch {
                    viewModel.deleteMusic(music)
                }
                findNavController().navigateUp()
            }
        }
    }

}