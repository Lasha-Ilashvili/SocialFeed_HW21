package com.example.socialfeed_hw22.domain.usecase

import com.example.socialfeed_hw22.domain.repository.story.StoriesRepository
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) {
    suspend operator fun invoke() =
        storiesRepository.getStories()
}