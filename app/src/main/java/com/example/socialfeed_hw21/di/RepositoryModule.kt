package com.example.socialfeed_hw21.di

import com.example.socialfeed_hw21.data.common.HandleResponse
import com.example.socialfeed_hw21.data.repository.PostsRepositoryImpl
import com.example.socialfeed_hw21.data.service.PostsService
import com.example.socialfeed_hw21.domain.repository.PostsRepository
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
        itemsService: PostsService,
        handleResponse: HandleResponse
    ): PostsRepository {
        return PostsRepositoryImpl(
            postsService = itemsService,
            handleResponse = handleResponse
        )
    }
}