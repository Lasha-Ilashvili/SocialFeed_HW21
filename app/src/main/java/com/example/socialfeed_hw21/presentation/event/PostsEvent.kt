package com.example.socialfeed_hw21.presentation.event

sealed class PostsEvent {
    data object ResetErrorMessage : PostsEvent()
}