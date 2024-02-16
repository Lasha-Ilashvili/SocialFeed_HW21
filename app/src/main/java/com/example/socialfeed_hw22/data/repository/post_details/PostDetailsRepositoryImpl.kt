package com.example.socialfeed_hw22.data.repository.post_details

import com.example.socialfeed_hw22.data.common.HandleResponse
import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.data.mapper.base.asResource
import com.example.socialfeed_hw22.data.mapper.post.toDomain
import com.example.socialfeed_hw22.data.service.post_details.PostDetailsService
import com.example.socialfeed_hw22.domain.model.PostDomain
import com.example.socialfeed_hw22.domain.repository.post_details.PostDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PostDetailsRepositoryImpl @Inject constructor(
    private val postDetailsService: PostDetailsService,
    private val handleResponse: HandleResponse,
) : PostDetailsRepository {

    override suspend fun getPostDetails(id: Int): Flow<Resource<PostDomain>> {
        return handleResponse.safeApiCall {
            postDetailsService.getPostDetails(id)
        }.asResource { dto ->
            dto.toDomain()
        }
    }
}