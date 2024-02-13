package com.example.socialfeed_hw21.domain.repository

import com.example.socialfeed_hw21.data.common.Resource
import com.example.socialfeed_hw21.domain.model.PostDomain
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getItems(): Flow<Resource<List<PostDomain>>>
}