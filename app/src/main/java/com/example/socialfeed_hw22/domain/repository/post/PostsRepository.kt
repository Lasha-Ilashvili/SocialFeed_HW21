package com.example.socialfeed_hw22.domain.repository.post

import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.domain.model.PostDomain
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(): Flow<Resource<List<PostDomain>>>
}