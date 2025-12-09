package com.example.portfolio.mvi_example.feature.login.presentation

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isLoggingIn: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null,
)
