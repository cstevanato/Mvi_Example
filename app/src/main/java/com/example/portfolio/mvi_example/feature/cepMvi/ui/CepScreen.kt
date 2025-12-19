package com.example.portfolio.mvi_example.feature.cepMvi.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.portfolio.mvi_example.feature.cepMvi.domain.model.Address
import kotlinx.coroutines.flow.Flow

@Composable
fun CepScreen(modifier: Modifier = Modifier, viewModel: CepViewModel = hiltViewModel()) {
    HandleEvents(viewModel.effect)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CepContent(modifier = modifier, uiState = uiState, onIntent = viewModel::setIntent)
}

@Composable
fun CepContent(modifier: Modifier = Modifier, uiState: CepUiState, onIntent: (CepIntent) -> Unit) {
    Column(
        modifier =
            modifier.fillMaxWidth().height(320.dp).padding(horizontal = 15.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Try to find CEP",
            style = MaterialTheme.typography.headlineSmall,
        )

        EditTextName(searchGender = { onIntent(CepIntent.SearchAddress(it)) })

        CepInfo(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            isLoading = uiState.isLoading,
            Address = uiState.address,
        )
    }
}

@Composable
fun CepInfo(modifier: Modifier, isLoading: Boolean, Address: Address?) {
    AnimatedContent(targetState = isLoading, label = "Animated Content") { targetState ->
        Box(modifier = modifier) {
            if (targetState) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Address?.let { info ->
                    Card(
                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    when (info.ddd) {
                                        "19" -> MaterialTheme.colorScheme.primary
                                        else -> MaterialTheme.colorScheme.tertiary
                                    }
                            ),
                        elevation = CardDefaults.cardElevation(5.dp),
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                                Text(
                                    text = "Cep: ${info.cep}",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Text(
                                    text = "Logradouro: ${info.logradouro}",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Text(
                                    text = "Bairro: ${info.bairro}",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Text(
                                    text = "Localidade: ${info.localidade}",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Text(
                                    text = "UF: ${info.uf}",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Text(
                                    text = "DDD: ${info.ddd}",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HandleEvents(events: Flow<CepEvent>) {
    val context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            events.collect { event ->
                when (event) {
                    is CepEvent.ShowToast -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}

@Composable
fun EditTextName(searchGender: (String) -> Unit) {
    var name by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = name,
        onValueChange = { name = it },
        label = { Text("Cep") },
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { searchGender(name) }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        },
        placeholder = { Text("Enter the name") },
    )
}
