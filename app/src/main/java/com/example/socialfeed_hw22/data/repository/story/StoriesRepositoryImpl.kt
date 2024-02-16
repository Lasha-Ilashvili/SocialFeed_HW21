package com.example.socialfeed_hw22.data.repository.story

import com.example.socialfeed_hw22.data.common.HandleResponse
import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.data.mapper.base.asResource
import com.example.socialfeed_hw22.data.mapper.story.toDomain
import com.example.socialfeed_hw22.data.service.story.StoriesService
import com.example.socialfeed_hw22.domain.model.Story
import com.example.socialfeed_hw22.domain.repository.story.StoriesRepository
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
