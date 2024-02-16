package com.example.socialfeed_hw22.presentation.screen.post_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfeed_hw22.databinding.ImageItemBinding
import com.example.socialfeed_hw22.presentation.extension.loadImage

class PostImagesRecyclerViewAdapter :
    RecyclerView.Adapter<PostImagesRecyclerViewAdapter.ImagesViewHolder>() {

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
            with(binding) {
                ivPostImages.loadImage(images[adapterPosition])

                ivPostImages.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                ivPostImages.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

                (cvBg.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    marginEnd = 0
                    marginStart = 100
                }
            }
        }
    }
}