package com.example.portfolio.mvi_example.feature.post.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@Composable
fun PostScreen(
    viewModel: PostViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val state by viewModel.collectAsState()
    val context = LocalContext.current

    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(5.dp)) {
        items(state.posts) { post ->
            Text(
                text = post.title,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
            )
        }
    }
    if (state.progressBar) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    viewModel.collectSideEffect() {
        when (it) {
            is UIComponent.Toast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
}
