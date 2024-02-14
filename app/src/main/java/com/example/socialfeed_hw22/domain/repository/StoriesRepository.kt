package com.example.socialfeed_hw22.domain.repository

import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    suspend fun getStories(): Flow<Resource<List<Story>>>
}