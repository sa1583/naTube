package fastcampus.aop.part2.natube.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.aop.part2.natube.databinding.ItemVideoBinding
import fastcampus.aop.part2.natube.model.VideoModel

class VideoAdapter: ListAdapter<VideoModel, VideoAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoModel) {
            binding.apply {
                titleTextView.text = item.title
                subtitleTextView.text = item.subtitle

                Glide.with(thumbnailImageView)
                    .load(item.thumb)
                    .into(thumbnailImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    companion object {
        private val diffUtil = object: DiffUtil.ItemCallback<VideoModel>() {
            override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}