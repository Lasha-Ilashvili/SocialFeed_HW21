package com.example.socialfeed_hw22.presentation.event.home

sealed class HomeEvent {
    data object ResetErrorMessage : HomeEvent()
}