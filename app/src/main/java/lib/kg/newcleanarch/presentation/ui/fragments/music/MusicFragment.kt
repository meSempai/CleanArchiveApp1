package lib.kg.newcleanarch.presentation.ui.fragments.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import lib.kg.newcleanarch.R
import lib.kg.newcleanarch.databinding.FragmentMusicBinding
import lib.kg.newcleanarch.domain.models.Music
import lib.kg.newcleanarch.presentation.ui.base.BaseFragment
import lib.kg.newcleanarch.presentation.ui.fragments.music.adapter.MusicAdapter

@AndroidEntryPoint
class MusicFragment : BaseFragment<FragmentMusicBinding>() {
    private val viewModel by viewModels<MusicViewModel>()
    private val adapter = MusicAdapter()
    private var isSortPerfomer = false
    private var isSortDuration = false
    private var isSortDefault = true

    private fun adapterListener() {
        adapter.onItemClick = {
            findNavController().navigate(
                R.id.detailFragment,
                bundleOf(KEY_MUSIC to it)
            )
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMusicBinding {
        return FragmentMusicBinding.inflate(layoutInflater)
    }

    override fun uiBox() {
        initListeners()
        viewModelListener()
        adapterListener()
    }

    private fun initListeners() {
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
            btnSortByDuration.setOnClickListener {
                isSortDuration = true
            }
        }
    }

    private fun viewModelListener() {
        viewModel.getMusicByDuration()
        viewModel.getAllMusicStates.collectState(
            {
                binding.progressbar.isVisible = false
            },
            {
                binding.progressbar.isVisible = false
                adapter.addMusicList(it)
                binding.rvMusic.adapter = adapter
            }
        )


//        viewModel.getMusicByPerfomer()
//        viewModel.getAllMusicStates.collectState(
//            {
//                binding.progressbar.isVisible = false
//            },
//            {
//                binding.progressbar.isVisible = false
//                adapter.addMusicList(it)
//                binding.rvMusic.adapter = adapter
//            }
//        )
//
//        viewModel.getAllMusic()
//        viewModel.getAllMusicStates.collectState(
//            {
//                binding.progressbar.isVisible = true
//            },
//            {
//                binding.progressbar.isVisible = false
//                adapter.addMusicList(it)
//                binding.rvMusic.adapter = adapter
//            }
//        )
    }

    companion object {
        const val KEY_MUSIC = "musyka"
    }
}