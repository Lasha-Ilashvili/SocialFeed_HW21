package com.example.socialfeed_hw21.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfeed_hw21.databinding.PostItemBinding
import com.example.socialfeed_hw21.databinding.StoryRecyclerBinding
import com.example.socialfeed_hw21.domain.model.Story
import com.example.socialfeed_hw21.presentation.model.Post

class HomeRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var posts: List<Post>

    private lateinit var stories: List<Story>

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

    inner class StoryViewHolder(private val storyRecyclerBinding: StoryRecyclerBinding) :
        RecyclerView.ViewHolder(storyRecyclerBinding.root) {

        fun bind() {
            storyRecyclerBinding.rvStories.adapter = StoriesRecyclerViewAdapter().apply {
                setData(stories)
            }
        }
    }

    inner class PostViewHolder(private val postItemBinding: PostItemBinding) :
        RecyclerView.ViewHolder(postItemBinding.root) {

        fun bind() {

        }
    }
}