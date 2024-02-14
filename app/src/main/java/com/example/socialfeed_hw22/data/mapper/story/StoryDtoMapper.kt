package com.example.socialfeed_hw22.data.mapper.story

import com.example.socialfeed_hw22.data.model.story.StoryDto
import com.example.socialfeed_hw22.domain.model.Story


fun StoryDto.toDomain(): Story {
    return Story(
        id = id,
        cover = cover,
        title = title
    )
}