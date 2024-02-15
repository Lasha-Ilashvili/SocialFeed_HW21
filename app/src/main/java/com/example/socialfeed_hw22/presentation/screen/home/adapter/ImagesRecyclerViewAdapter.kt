package com.example.socialfeed_hw22.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.socialfeed_hw22.databinding.ImageItemBinding
import com.example.socialfeed_hw22.presentation.extension.loadImage

class ImagesRecyclerViewAdapter :
    RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImagesViewHolder>() {

    private lateinit var images: List<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setData(newImages: List<String>) {
        images = newImages
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind()
    }

    inner class ImagesViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val image = images[adapterPosition]
            binding.ivPostImages.loadImage(image)

            val layoutParams =
                itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams;

            if (adapterPosition == 0)
                layoutParams.isFullSpan = true

            if (adapterPosition == 1 && images.size < 3)
                layoutParams.isFullSpan = true
        }
    }
}