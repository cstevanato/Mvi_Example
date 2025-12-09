package com.example.portfolio.mvi_example.feature.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginCore(
    viewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onNavigateToBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is LoginEvent.LoginSuccess -> {
                    onNavigateToBack.invoke()
                }
            }
        }
    }

}

@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = state.email,
            onValueChange = { onAction(LoginAction.EmailChanged(it)) },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Email") },
            isError = !state.isEmailValid
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = { onAction(LoginAction.PasswordChanged(it)) },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Password") },
            isError = !state.isPasswordValid
        )

        if (state.isLoggingIn) {
            CircularProgressIndicator()
        }
        else {
            Button(onClick = { onAction(LoginAction.LoginAttempted) }) {
                if (state.isLoggingIn)
                    CircularProgressIndicator()
                else
                    Text(text = "Login")
            }
        }    }
}


