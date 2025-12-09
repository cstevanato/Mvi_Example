package com.example.portfolio.mvi_example.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _event = Channel<LoginEvent>()
    val event = _event.receiveAsFlow()


    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EmailChanged -> {
                _state.update { it.copy(email = action.email) }
            }

            is LoginAction.PasswordChanged -> {
                _state.update { it.copy(password = action.password) }
            }

            is LoginAction.LoginAttempted -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoggingIn = true) }
            delay(2000L)
            _state.update { it.copy(isLoggingIn = false, isLoggedIn = true) }
            delay(500L)
            _event.send(LoginEvent.LoginSuccess)
        }
    }
}

