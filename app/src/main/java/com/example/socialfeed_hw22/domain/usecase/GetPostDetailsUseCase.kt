package com.example.socialfeed_hw22.domain.usecase

import com.example.socialfeed_hw22.domain.repository.post_details.PostDetailsRepository
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val postDetailsRepository: PostDetailsRepository
) {
    suspend operator fun invoke(id: Int) =
        postDetailsRepository.getPostDetails(id)
}