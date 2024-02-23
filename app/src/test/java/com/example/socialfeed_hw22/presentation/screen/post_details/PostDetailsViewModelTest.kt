package com.example.socialfeed_hw22.presentation.screen.post_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.socialfeed_hw22.MainCoroutineRule
import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.domain.model.OwnerDomain
import com.example.socialfeed_hw22.domain.model.PostDomain
import com.example.socialfeed_hw22.domain.usecase.GetPostDetailsUseCase
import com.example.socialfeed_hw22.presentation.event.post_details.PostDetailsEvent
import com.example.socialfeed_hw22.presentation.mapper.post.toPresentation
import com.example.socialfeed_hw22.presentation.state.post_details.PostDetailsState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class PostDetailsViewModelTest {

    private lateinit var viewModel: PostDetailsViewModel
    private val postDetailsUseCase = mockk<GetPostDetailsUseCase>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = PostDetailsViewModel(postDetailsUseCase = postDetailsUseCase)

        mockPostDetails()
    }

    private fun mockPostDetails() = PostDomain(
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

    @Test
    fun `PostDetailsState is updated with SetPostDetails event, case - Success`() = runTest {
        // Given
        val postId = Random.nextInt()
        val postDetails = mockPostDetails().copy(id = postId)
        val postDetailsResource = Resource.Success(postDetails)
        coEvery { postDetailsUseCase(postId) } returns flowOf(postDetailsResource)

        // When
        viewModel.onEvent(PostDetailsEvent.SetPostDetails(postId))

        // Then
        val expectedViewState = PostDetailsState(
            data = postDetails.toPresentation(),
            isLoading = true,
            errorMessage = null
        )
        assertEquals(expectedViewState, viewModel.postDetails.value)
        coVerify(exactly = 1) { postDetailsUseCase(postId) }
    }

    @Test
    fun `PostDetailsState is updated with SetPostDetails event, case - Error`() = runTest {
        // Given
        val postId = Random.nextInt()
        val errorMessage = "Network error"
        val errorResource = Resource.Error(errorMessage)
        coEvery { postDetailsUseCase(postId) } returns flowOf(errorResource)

        // When
        viewModel.onEvent(PostDetailsEvent.SetPostDetails(postId))

        // Then
        val expectedViewState = PostDetailsState(
            data = null,
            isLoading = true,
            errorMessage = errorMessage
        )
        assertEquals(expectedViewState, viewModel.postDetails.value)
        coVerify(exactly = 1) { postDetailsUseCase(postId) }
    }

    @Test
    fun `PostDetailsState errorMessage is null with ResetErrorMessage event`() = runTest {
        // Given
        val postId = Random.nextInt()
        val errorMessage = "Network error"
        val errorResource = Resource.Error(errorMessage)
        coEvery { postDetailsUseCase(postId) } returns flowOf(errorResource)
        viewModel.onEvent(PostDetailsEvent.SetPostDetails(postId))
        viewModel.onEvent(PostDetailsEvent.ResetErrorMessage)

        // When
        viewModel.onEvent(PostDetailsEvent.ResetErrorMessage)

        // Then
        val expectedViewState = PostDetailsState(
            data = null,
            isLoading = true,
            errorMessage = null
        )
        assertEquals(expectedViewState, viewModel.postDetails.value)
    }
}