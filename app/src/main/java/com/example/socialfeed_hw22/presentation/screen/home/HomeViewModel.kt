package com.example.socialfeed_hw22.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.domain.usecase.GetPostsUseCase
import com.example.socialfeed_hw22.domain.usecase.GetStoriesUseCase
import com.example.socialfeed_hw22.presentation.event.home.HomeEvent
import com.example.socialfeed_hw22.presentation.mapper.post.toPresentation
import com.example.socialfeed_hw22.presentation.state.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPosts: GetPostsUseCase,
    private val getStories: GetStoriesUseCase
) : ViewModel() {

    private val _feed = MutableStateFlow(HomeState())
    val feed get() = _feed.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SetStories -> setStories()
            is HomeEvent.SetPosts -> setPosts()
            is HomeEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun setStories() {
        viewModelScope.launch {
            getStories().collect {
                when (it) {
                    is Resource.Success -> {
                        _feed.update { currentState ->
                            currentState.copy(stories = it.data)
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _feed.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun setPosts() {
        viewModelScope.launch {
            getPosts().collect {
                when (it) {
                    is Resource.Success -> {
                        _feed.update { currentState ->
                            currentState.copy(posts = it.data.map { domain ->
                                domain.toPresentation()
                            })
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _feed.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String? = null) {
        _feed.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }
}