package org.company.annamedvedieva.imagebrowser.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.company.annamedvedieva.imagebrowser.data.ImageItem
import org.company.annamedvedieva.imagebrowser.databinding.PhotoItemBinding

class ImageGridAdapter :
    ListAdapter<ImageItem, ImageGridAdapter.ImageItemViewHolder>(DiffCallback) {

    class ImageItemViewHolder(private var binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageItem: ImageItem) {
            binding.image = imageItem
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ImageItem>() {
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        return ImageItemViewHolder(PhotoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        val imageItem = getItem(position)
        holder.bind(imageItem)
    }
}


