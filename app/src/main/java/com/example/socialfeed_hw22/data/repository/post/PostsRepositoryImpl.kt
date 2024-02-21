package com.example.socialfeed_hw22.data.repository.post

import com.example.socialfeed_hw22.data.common.HandleResponse
import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.data.mapper.base.asResource
import com.example.socialfeed_hw22.data.mapper.post.toDomain
import com.example.socialfeed_hw22.data.service.post.PostsService
import com.example.socialfeed_hw22.domain.model.PostDomain
import com.example.socialfeed_hw22.domain.repository.post.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsService: PostsService,
    private val handleResponse: HandleResponse,
) : PostsRepository {

    override suspend fun getPosts(): Flow<Resource<List<PostDomain>>> {
        return handleResponse.safeApiCall {
            postsService.getPosts()
        }.asResource {
            it.map { dto ->
                dto.toDomain()
            }
        }
    }

    override suspend fun getPostDetails(id: Int): Flow<Resource<PostDomain>> {
        return handleResponse.safeApiCall {
            postsService.getPostDetails(id)
        }.asResource { dto ->
            dto.toDomain()
        }
    }
}
