package com.example.socialfeed_hw21.domain.usecase

import com.example.socialfeed_hw21.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke() =
        postsRepository.getPosts()
}