package com.example.socialfeed_hw21.presentation.state

import com.example.socialfeed_hw21.domain.model.Story
import com.example.socialfeed_hw21.presentation.model.Post


data class FeedState(
    val isLoading: Boolean = false,
    val stories: List<Story>? = null,
    val posts: List<Post>? = null,
    val errorMessage: String? = null
)