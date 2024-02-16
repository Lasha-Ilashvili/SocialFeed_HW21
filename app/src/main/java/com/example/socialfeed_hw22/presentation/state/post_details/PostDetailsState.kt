package com.example.socialfeed_hw22.presentation.state.post_details

import com.example.socialfeed_hw22.presentation.model.Post


data class PostDetailsState(
    val isLoading: Boolean = true,
    val data: Post? = null,
    val errorMessage: String? = null
)