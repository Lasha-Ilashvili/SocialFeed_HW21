package com.example.socialfeed_hw22.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfeed_hw22.databinding.PostItemBinding
import com.example.socialfeed_hw22.presentation.model.Post

class PostsRecyclerViewAdapter : RecyclerView.Adapter<PostsRecyclerViewAdapter.PostsViewHolder>() {

    private lateinit var posts: List<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setData(newPosts: List<Post>) {
        posts = newPosts
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind()
    }

    inner class PostsViewHolder(private val postItemBinding: PostItemBinding) :
        RecyclerView.ViewHolder(postItemBinding.root) {

        fun bind() {
        }
    }
}