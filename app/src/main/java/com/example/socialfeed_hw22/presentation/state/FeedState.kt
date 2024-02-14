package com.example.socialfeed_hw22.presentation.state

import com.example.socialfeed_hw22.domain.model.Story
import com.example.socialfeed_hw22.presentation.model.Post


data class FeedState(
    val isLoading: Boolean = false,
    val stories: List<Story>? = null,
    val posts: List<Post>? = null,
    val errorMessage: String? = null
)