package com.example.socialfeed_hw22.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.socialfeed_hw22.databinding.ImageItemBinding
import com.example.socialfeed_hw22.presentation.extension.loadImage

class ImagesRecyclerViewAdapter :
    RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImagesViewHolder>() {

    private lateinit var images: List<String>

    var onImageClick: (() -> Unit)? = null

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

        init {
            binding.root.setOnClickListener {
                onImageClick?.invoke()
            }
        }

        fun bind() {
            val image = images[adapterPosition]
            binding.ivPostImages.loadImage(image)

            setImageGrid()
        }

        private fun setImageGrid() {
            val layoutParams =
                itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams;

            val dynamicSize = images.size % 3
            val dynamicPosition = adapterPosition % 3

            if (dynamicPosition == 0) {
                layoutParams.isFullSpan = true

                if (dynamicSize == 1) {
                    binding.ivPostImages.layoutParams.width = MATCH_PARENT

                    (binding.cvBg.layoutParams as ViewGroup.MarginLayoutParams).apply {
                        marginEnd = 0
                        marginStart = 100
                    }
                }
            }
            if (dynamicPosition == 1 && dynamicSize == 2)
                layoutParams.isFullSpan = true
        }
    }
}