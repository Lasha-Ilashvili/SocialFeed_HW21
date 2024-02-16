package com.example.socialfeed_hw22.presentation.event.post_details


sealed class PostDetailsEvent {
    data class SetPostDetails(val id: Int) : PostDetailsEvent()
    data object ResetErrorMessage : PostDetailsEvent()
}