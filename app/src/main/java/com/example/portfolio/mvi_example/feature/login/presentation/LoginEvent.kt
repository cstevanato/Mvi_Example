package com.example.portfolio.mvi_example.feature.login.presentation

sealed interface LoginEvent {
    data object LoginSuccess : LoginEvent
}
