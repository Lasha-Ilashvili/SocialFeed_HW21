package com.example.socialfeed_hw22.presentation.state.home

import com.example.socialfeed_hw22.domain.model.Story
import com.example.socialfeed_hw22.presentation.model.Post


data class HomeState(
    val isLoading: Boolean = false,
    val stories: List<Story>? = null,
    val posts: List<Post>? = null,
    val errorMessage: String? = null
)