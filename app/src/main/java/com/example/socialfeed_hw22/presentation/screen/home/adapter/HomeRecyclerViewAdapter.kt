package com.example.socialfeed_hw22.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfeed_hw22.databinding.PostItemBinding
import com.example.socialfeed_hw22.databinding.StoryRecyclerBinding
import com.example.socialfeed_hw22.domain.model.Story
import com.example.socialfeed_hw22.presentation.extension.loadImage
import com.example.socialfeed_hw22.presentation.model.Post

class HomeRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var posts: List<Post>

    private lateinit var stories: List<Story>

    var onClick: ((Int) -> Unit)? = null

    private companion object {
        const val POSTS = 0
        const val STORIES = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> STORIES
            else -> POSTS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            STORIES -> StoryViewHolder(
                StoryRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> PostViewHolder(
                PostItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun setStories(newStories: List<Story>) {
        stories = newStories
    }


    fun setPosts(newPosts: List<Post>) {
        posts = newPosts
    }

    override fun getItemCount() = posts.size + STORIES

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StoryViewHolder -> holder.bind()
            is PostViewHolder -> holder.bind()
        }
    }

    inner class StoryViewHolder(private val binding: StoryRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.rvStories.adapter = StoriesRecyclerViewAdapter().apply {
                setData(stories)
            }
        }
    }

    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val post = posts[adapterPosition - 1]
            val owner = post.owner
            val username = "${owner.firstName} ${owner.lastName}"

            with(binding) {
                if (owner.profile.isNullOrBlank()) {
                    ivPfp.visibility = GONE
                    resetUsernameMargin(tvUsername)
                } else {
                    ivPfp.loadImage(post.owner.profile)
                }

                tvUsername.text = username
                tvDate.text = owner.postDate
                tvCommentNumber.text = post.comments.toString()
                tvLikeNumber.text = post.likes.toString()
            }

            setImageGrid(post.images, post)
        }

        private fun setImageGrid(images: List<String>?, post: Post) {
            images?.let {
                binding.rvImages.adapter = ImagesRecyclerViewAdapter().apply {
                    onImageClick = {
                        onClick?.invoke(post.id)
                    }
                    setData(it)
                }
            }
        }

        private fun resetUsernameMargin(tvUsername: AppCompatTextView) {
            (tvUsername.layoutParams as ViewGroup.MarginLayoutParams).apply {
                marginStart = 0
            }
        }
    }
}