package com.example.socialfeed_hw21.presentation.event

sealed class FeedEvent {
    data object ResetErrorMessage : FeedEvent()
}