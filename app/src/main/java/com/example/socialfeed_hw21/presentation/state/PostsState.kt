package com.example.socialfeed_hw21.presentation.state

import com.example.socialfeed_hw21.presentation.model.Post


data class PostsState(
    val isLoading: Boolean = false,
    val data: List<Post>? = null,
    val errorMessage: String? = null
)