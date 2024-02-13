package com.example.socialfeed_hw21.data.service.post

import com.example.socialfeed_hw21.data.model.post.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {
    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPosts(): Response<List<PostDto>>
}