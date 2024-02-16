package com.example.socialfeed_hw22.domain.repository.post_details

import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.domain.model.PostDomain
import kotlinx.coroutines.flow.Flow

interface PostDetailsRepository {
    suspend fun getPostDetails(id:Int): Flow<Resource<PostDomain>>
}