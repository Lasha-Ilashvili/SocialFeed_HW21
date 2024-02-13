package com.example.socialfeed_hw21.presentation.model

import com.example.socialfeed_hw21.domain.model.Story

data class Feed(
    val story: List<Story>? = null,
    val post: List<Post>? = null
)