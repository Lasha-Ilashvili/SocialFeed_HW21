package com.example.socialfeed_hw21.domain.repository

import com.example.socialfeed_hw21.data.common.Resource
import com.example.socialfeed_hw21.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    suspend fun getStories(): Flow<Resource<List<Story>>>
}