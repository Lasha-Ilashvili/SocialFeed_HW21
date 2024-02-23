package com.example.socialfeed_hw22.presentation.screen.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.socialfeed_hw22.MainCoroutineRule
import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.domain.model.OwnerDomain
import com.example.socialfeed_hw22.domain.model.PostDomain
import com.example.socialfeed_hw22.domain.model.Story
import com.example.socialfeed_hw22.domain.usecase.GetPostsUseCase
import com.example.socialfeed_hw22.domain.usecase.GetStoriesUseCase
import com.example.socialfeed_hw22.presentation.event.home.HomeEvent
import com.example.socialfeed_hw22.presentation.mapper.post.toPresentation
import com.example.socialfeed_hw22.presentation.state.home.HomeState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    private val getPostsUseCase = mockk<GetPostsUseCase>()
    private val getStoriesUseCase = mockk<GetStoriesUseCase>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(getPosts = getPostsUseCase, getStories = getStoriesUseCase)

        mockHome()
    }

    private fun mockHome() {
        mockPosts()
        mockStories()
    }

    private fun mockPosts() = listOf(
        PostDomain(
            id = 0,
            images = listOf(),
            title = "",
            comments = 0,
            likes = 0,
            shareContent = "",
            owner = OwnerDomain(
                firstName = "",
                lastName = "",
                profile = null,
                postDate = 0
            )
        )
    )

    private fun mockStories() = listOf(Story(id = 0, cover = "", title = ""))

    @Test
    fun `HomeState is updated with SetStories event, case - Success`() = runTest {
        // Given
        val stories = mockStories()
        val storiesResource = Resource.Success(stories)
        coEvery { getStoriesUseCase() } returns flowOf(storiesResource)

        // When
        viewModel.onEvent(HomeEvent.SetStories)

        // Then
        val expectedViewState = HomeState(
            isLoading = false,
            stories = stories,
            posts = null,
            errorMessage = null
        )
        Assert.assertEquals(expectedViewState, viewModel.feed.value)
        coVerify(exactly = 1) { getStoriesUseCase() }
    }

    @Test
    fun `HomeState is updated with SetStories event, case - Error`() = runTest {
        // Given
        val errorMessage = "Network error"
        val errorResource = Resource.Error(errorMessage)

        coEvery { getStoriesUseCase() } returns flowOf(errorResource)

        // When
        viewModel.onEvent(HomeEvent.SetStories)

        // Then
        val expectedViewState = HomeState(
            isLoading = false,
            stories = null,
            posts = null,
            errorMessage = errorMessage
        )
        Assert.assertEquals(expectedViewState, viewModel.feed.value)
        coVerify(exactly = 1) { getStoriesUseCase() }
    }

    @Test
    fun `HomeState is updated with SetPosts event, case - Success`() = runTest {
        // Given
        val posts = mockPosts()
        val postsResource = Resource.Success(posts)
        coEvery { getPostsUseCase() } returns flowOf(postsResource)

        // When
        viewModel.onEvent(HomeEvent.SetPosts)

        // Then
        val expectedViewState = HomeState(
            isLoading = false,
            stories = null,
            posts = posts.map { it.toPresentation() },
            errorMessage = null
        )
        Assert.assertEquals(expectedViewState, viewModel.feed.value)
        coVerify(exactly = 1) { getPostsUseCase() }
    }

    @Test
    fun `HomeState is updated with SetPosts event, case - Error`() = runTest {
        // Given
        val errorMessage = "Network error"
        val errorResource = Resource.Error(errorMessage)

        coEvery { getPostsUseCase() } returns flowOf(errorResource)

        // When
        viewModel.onEvent(HomeEvent.SetPosts)

        // Then
        val expectedViewState = HomeState(
            isLoading = false,
            stories = null,
            posts = null,
            errorMessage = errorMessage
        )
        Assert.assertEquals(expectedViewState, viewModel.feed.value)
        coVerify(exactly = 1) { getPostsUseCase() }
    }

    @Test
    fun `HomeState errorMessage is null with ResetErrorMessage event`() = runTest {
        // Given
        val errorMessage = "Network error"
        val errorResource = Resource.Error(errorMessage)

        coEvery { getStoriesUseCase() } returns flowOf(errorResource)

        coEvery { getPostsUseCase() } returns flowOf(errorResource)

        // Trigger the events to set the stories and posts data
        viewModel.onEvent(HomeEvent.SetStories)
        viewModel.onEvent(HomeEvent.SetPosts)

        // When
        viewModel.onEvent(HomeEvent.ResetErrorMessage)

        // Then
        val expectedViewState = HomeState(
            isLoading = false,
            stories = null,
            posts = null,
            errorMessage = null
        )
        Assert.assertEquals(expectedViewState, viewModel.feed.value)
    }
}