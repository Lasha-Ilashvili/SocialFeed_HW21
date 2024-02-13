package com.example.socialfeed_hw21.data.repository

import com.example.socialfeed_hw21.data.common.HandleResponse
import com.example.socialfeed_hw21.data.common.Resource
import com.example.socialfeed_hw21.data.mapper.base.asResource
import com.example.socialfeed_hw21.data.mapper.post.toDomain
import com.example.socialfeed_hw21.data.service.PostsService
import com.example.socialfeed_hw21.domain.model.PostDomain
import com.example.socialfeed_hw21.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsService: PostsService,
    private val handleResponse: HandleResponse,
) : PostsRepository {

    override suspend fun getItems(): Flow<Resource<List<PostDomain>>> {
        return handleResponse.safeApiCall {
            postsService.getPosts()
        }.asResource {
            it.map { dto ->
                dto.toDomain()
            }
        }
    }
}
