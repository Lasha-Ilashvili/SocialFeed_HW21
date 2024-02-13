package com.example.socialfeed_hw21.presentation.state

import com.example.socialfeed_hw21.presentation.model.Feed


data class FeedState(
    val isLoading: Boolean = false,
    val data: Feed? = null,
    val errorMessage: String? = null
)