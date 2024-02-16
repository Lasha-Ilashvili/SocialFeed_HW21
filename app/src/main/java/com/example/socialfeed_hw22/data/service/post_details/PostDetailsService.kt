package com.example.socialfeed_hw22.data.service.post_details

import com.example.socialfeed_hw22.data.model.post.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostDetailsService {
    @GET("d02b76bb-095d-45fa-90e1-dc4733d1f247?id=3")
    suspend fun getPostDetails(
        @Query("id") id: Int
    ): Response<PostDto>
}