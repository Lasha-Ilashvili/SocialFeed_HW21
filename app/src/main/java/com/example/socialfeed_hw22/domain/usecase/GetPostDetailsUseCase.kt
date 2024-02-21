package com.example.socialfeed_hw22.domain.usecase

import com.example.socialfeed_hw22.domain.repository.post.PostsRepository
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(id: Int) =
        postsRepository.getPostDetails(id)
}