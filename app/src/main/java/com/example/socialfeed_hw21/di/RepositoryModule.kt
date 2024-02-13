package com.example.socialfeed_hw21.di

import com.example.socialfeed_hw21.data.common.HandleResponse
import com.example.socialfeed_hw21.data.repository.post.PostsRepositoryImpl
import com.example.socialfeed_hw21.data.repository.story.StoriesRepositoryImpl
import com.example.socialfeed_hw21.data.service.post.PostsService
import com.example.socialfeed_hw21.data.service.story.StoriesService
import com.example.socialfeed_hw21.domain.repository.PostsRepository
import com.example.socialfeed_hw21.domain.repository.StoriesRepository
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