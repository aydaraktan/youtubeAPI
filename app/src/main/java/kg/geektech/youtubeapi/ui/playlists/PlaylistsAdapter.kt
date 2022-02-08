package kg.geektech.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.kotlin1lesson5.core.extensions.load
import kg.geektech.youtubeapi.databinding.ListPlaylistBinding
import kg.geektech.youtubeapi.model.Item

class PlaylistsAdapter(): RecyclerView.Adapter<PlaylistsAdapter.MainViewHolder>() {
    private lateinit var onItemClick: OnItemClick
    private var list = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PlaylistsAdapter.MainViewHolder {
        val binding =ListPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistsAdapter.MainViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Item>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

     fun setOnClick(onItemClick: OnItemClick){
        this.onItemClick = onItemClick
    }

    inner class MainViewHolder(private val binding: ListPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Item) {
            item.snippet?.thumbnails?.high?.url?.let { binding.ivBanner.load(it) }
            binding.tvTitle.text = item.snippet?.title
            val videoCount = item.contentDetails?.itemCount.toString() + " video series"
            binding.tvVideoCount.text = videoCount

            itemView.setOnClickListener {
                onItemClick.onClick(item)
            }
        }
    }

    interface OnItemClick {
        fun onClick(item: Item)
    }
}