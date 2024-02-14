package com.example.socialfeed_hw22.presentation.event

sealed class FeedEvent {
    data object ResetErrorMessage : FeedEvent()
}