package com.example.socialfeed_hw21.data.model.story


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryDto(
    val id: Int,
    val cover: String,
    val title: String
)