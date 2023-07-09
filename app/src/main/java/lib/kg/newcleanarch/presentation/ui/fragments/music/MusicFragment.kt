package lib.kg.newcleanarch.presentation.ui.fragments.music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import lib.kg.newcleanarch.R
import lib.kg.newcleanarch.databinding.FragmentMusicBinding
import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.presentation.ui.base.BaseFragment
import lib.kg.newcleanarch.presentation.ui.fragments.music.adapter.MusicAdapter
import lib.kg.newcleanarch.presentation.utils.UiState

@AndroidEntryPoint
class MusicFragment : BaseFragment() {
    private lateinit var binding: FragmentMusicBinding
    private val viewModel by viewModels<MusicViewModel>()
    private val adapter = MusicAdapter()

    private fun adapterListener() {
        adapter.onItemClick = {
            findNavController().navigate(
                R.id.detailFragment,
                bundleOf(KEY_MUSIC to it)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListeners()
        viewModelListener()
        adapterListener()
    }

    private fun initOnClickListeners() {
        with(binding) {
            btnSave.setOnClickListener {
                viewModel.addMusic(
                    Music(
                        id = (0..9999).random(),
                        name = etName.text.toString(),
                        perfomer = etPerfomer.text.toString(),
                        duration = etDurationr.text.toString()
                    )
                )
            }
        }
    }

    private fun viewModelListener() {
        viewModel.getAllMusic()
        viewModel.getAllMusicStates.collectState(
            {
                binding.progressbar.isVisible = true
            },
            {
                binding.progressbar.isVisible = false
                binding.rvMusic.adapter = adapter
                adapter.addMusicList(it)
            }
        )
    }

    companion object {
        const val KEY_MUSIC = "musyka"
    }
}