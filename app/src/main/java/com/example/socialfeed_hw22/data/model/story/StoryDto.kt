package com.example.socialfeed_hw22.data.model.story


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryDto(
    val id: Int,
    val cover: String,
    val title: String
)