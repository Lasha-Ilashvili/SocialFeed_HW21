package com.example.socialfeed_hw21.domain.usecase

import com.example.socialfeed_hw21.domain.repository.StoriesRepository
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) {
    suspend operator fun invoke() =
        storiesRepository.getStories()
}