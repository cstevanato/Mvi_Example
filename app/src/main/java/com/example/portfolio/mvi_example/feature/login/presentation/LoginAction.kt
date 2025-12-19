package com.example.portfolio.mvi_example.feature.login.presentation

sealed interface LoginAction {
    data class EmailChanged(val email: String) : LoginAction

    data class PasswordChanged(val password: String) : LoginAction

    data object LoginAttempted : LoginAction
}
