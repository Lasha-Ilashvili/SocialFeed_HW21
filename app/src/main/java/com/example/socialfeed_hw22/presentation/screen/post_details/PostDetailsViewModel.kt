package com.example.socialfeed_hw22.presentation.screen.post_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialfeed_hw22.data.common.Resource
import com.example.socialfeed_hw22.domain.usecase.GetPostDetailsUseCase
import com.example.socialfeed_hw22.presentation.event.post_details.PostDetailsEvent
import com.example.socialfeed_hw22.presentation.mapper.post.toPresentation
import com.example.socialfeed_hw22.presentation.state.post_details.PostDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val postDetailsUseCase: GetPostDetailsUseCase
) : ViewModel() {

    private val _postDetails = MutableStateFlow(PostDetailsState())
    val postDetails get() = _postDetails.asStateFlow()

    fun onEvent(event: PostDetailsEvent) {
        when (event) {
            is PostDetailsEvent.SetPostDetails -> setPostDetails(event.id)

            is PostDetailsEvent.ResetErrorMessage -> updateErrorMessage()
        }
    }

    private fun setPostDetails(id: Int) {
        viewModelScope.launch {
            postDetailsUseCase(id).collect {
                when (it) {
                    is Resource.Success -> {
                        _postDetails.update { currentState ->
                            currentState.copy(
                                data = it.data.toPresentation()
                            )
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> _postDetails.update { currentState ->
                        currentState.copy(isLoading = it.loading)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String? = null) {
        _postDetails.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }
}