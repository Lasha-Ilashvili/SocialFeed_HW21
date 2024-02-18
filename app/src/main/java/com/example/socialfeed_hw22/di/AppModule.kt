package com.example.socialfeed_hw22.di

import com.example.socialfeed_hw22.BuildConfig.BASE_URL
import com.example.socialfeed_hw22.data.common.HandleResponse
import com.example.socialfeed_hw22.data.service.post.PostsService
import com.example.socialfeed_hw22.data.service.post_details.PostDetailsService
import com.example.socialfeed_hw22.data.service.story.StoriesService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Singleton
    @Provides
    fun providePostsService(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }

    @Singleton
    @Provides
    fun provideStoriesService(retrofit: Retrofit): StoriesService {
        return retrofit.create(StoriesService::class.java)
    }

    @Singleton
    @Provides
    fun providePostDetailsService(retrofit: Retrofit): PostDetailsService {
        return retrofit.create(PostDetailsService::class.java)
    }
}