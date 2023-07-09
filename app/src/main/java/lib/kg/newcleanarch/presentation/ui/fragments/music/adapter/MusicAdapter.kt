package lib.kg.newcleanarch.presentation.ui.fragments.music.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import lib.kg.newcleanarch.databinding.ItemMusicBinding
import lib.kg.newcleanarch.domain.models.Music

class MusicAdapter(
    var onItemClick: ((Music) -> Unit)? = null
) : Adapter<MusicAdapter.MusicViewHolder>() {

    private val musicList = arrayListOf<Music>()

    @SuppressLint("NotifyDataSetChanged")
    fun addMusicList(list: List<Music>) {
        musicList.clear()
        musicList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(
            ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(musicList[position])
    }

    inner class MusicViewHolder(private val binding: ItemMusicBinding) :
        ViewHolder(binding.root) {

        fun onBind(music: Music) {
            with(binding){
                tvName.text = music.name
                tvPerfomer.text = music.perfomer
                tvDuration.text = music.duration
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(music)
            }
        }
    }
}