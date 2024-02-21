package com.example.socialfeed_hw22.di

import com.example.socialfeed_hw22.data.common.HandleResponse
import com.example.socialfeed_hw22.data.repository.post.PostsRepositoryImpl
import com.example.socialfeed_hw22.data.repository.story.StoriesRepositoryImpl
import com.example.socialfeed_hw22.data.service.post.PostsService
import com.example.socialfeed_hw22.data.service.story.StoriesService
import com.example.socialfeed_hw22.domain.repository.post.PostsRepository
import com.example.socialfeed_hw22.domain.repository.story.StoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePostsRepository(
        postsService: PostsService,
        handleResponse: HandleResponse
    ): PostsRepository {
        return PostsRepositoryImpl(
            postsService = postsService,
            handleResponse = handleResponse
        )
    }

    @Singleton
    @Provides
    fun provideStoriesRepository(
        storiesService: StoriesService,
        handleResponse: HandleResponse
    ): StoriesRepository {
        return StoriesRepositoryImpl(
            storiesService = storiesService,
            handleResponse = handleResponse
        )
    }
}