package com.example.socialfeed_hw22.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfeed_hw22.databinding.StoryItemBinding
import com.example.socialfeed_hw22.domain.model.Story

class StoriesRecyclerViewAdapter :
    RecyclerView.Adapter<StoriesRecyclerViewAdapter.StoriesViewHolder>() {

    private lateinit var stories: List<Story>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        return StoriesViewHolder(
            StoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setData(newStories: List<Story>) {
        stories = newStories
    }

    override fun getItemCount() = stories.size

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.bind()
    }

    inner class StoriesViewHolder(private val storyItemBinding: StoryItemBinding) :
        RecyclerView.ViewHolder(storyItemBinding.root) {

        fun bind() {
//            storyRecyclerBinding.rvStories.adapter
//            rvNewCourses.adapter = NewCourseItemAdapter().apply {
//                    setNewCourseData(stories)
//                }
//            }
        }
    }
}