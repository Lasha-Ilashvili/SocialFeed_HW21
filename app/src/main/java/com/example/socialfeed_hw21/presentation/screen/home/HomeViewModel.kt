package com.example.socialfeed_hw21.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialfeed_hw21.data.common.Resource
import com.example.socialfeed_hw21.domain.usecase.GetPostsUseCase
import com.example.socialfeed_hw21.presentation.event.PostsEvent
import com.example.socialfeed_hw21.presentation.mapper.post.toPresentation
import com.example.socialfeed_hw21.presentation.state.PostsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPosts: GetPostsUseCase
) : ViewModel() {

    private val _posts = MutableStateFlow(PostsState())
    val posts get() = _posts.asStateFlow()

    init {
        setInitialList()
    }

    fun onEvent(event: PostsEvent) {
        when (event) {
            is PostsEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun setInitialList() {
        viewModelScope.launch {
            getPosts().collect {
                when (it) {
                    is Resource.Success -> {
                        _posts.update { currentState ->
                            currentState.copy(data = it.data.map { domain ->
                                domain.toPresentation()
                            })
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _posts.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String? = null) {
        _posts.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }
}