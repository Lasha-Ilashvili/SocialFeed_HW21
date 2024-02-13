package com.example.socialfeed_hw21.data.repository.story

import com.example.socialfeed_hw21.data.common.HandleResponse
import com.example.socialfeed_hw21.data.common.Resource
import com.example.socialfeed_hw21.data.mapper.base.asResource
import com.example.socialfeed_hw21.data.mapper.story.toDomain
import com.example.socialfeed_hw21.data.service.story.StoriesService
import com.example.socialfeed_hw21.domain.model.Story
import com.example.socialfeed_hw21.domain.repository.StoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(
    private val storiesService: StoriesService,
    private val handleResponse: HandleResponse,
) : StoriesRepository {

    override suspend fun getStories(): Flow<Resource<List<Story>>> {
        return handleResponse.safeApiCall {
            storiesService.getStories()
        }.asResource {
            it.map { dto ->
                dto.toDomain()
            }
        }
    }
}
