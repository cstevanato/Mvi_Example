package com.example.portfolio.mvi_example.feature.mvi.view

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.portfolio.mvi_example.feature.mvi.intent.TodoIntent
import com.example.portfolio.mvi_example.feature.mvi.model.loca.Todo

@Composable
fun TodoCore(modifier: Modifier = Modifier, viewModel: TodoViewModel = hiltViewModel()) {
    val list by viewModel.getAllTodos().collectAsState(initial = emptyList())
    TodoScreen(modifier = modifier, list = list, onIntent = viewModel::sendIntent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TodoScreen(
    modifier: Modifier = Modifier,
    list: List<Todo>,
    onIntent: (TodoIntent) -> Unit,
) {
    val title = remember { mutableStateOf("") }
    Column(modifier = modifier) {
        if (list.isEmpty()) {
            Box(modifier = modifier.weight(1f).fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Nothing found")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(list) {
                    val isChecked = remember { mutableStateOf(it.isDone) }
                    println(it)
                    Column(
                        modifier =
                            Modifier.combinedClickable(
                                    enabled = true,
                                    onClick = {},
                                    onLongClick = { onIntent(TodoIntent.DeleteTodo(it)) },
                                )
                                .fillMaxWidth()
                    ) {
                        Row(
                            modifier =
                                Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                    .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = it.title)
                            Checkbox(
                                checked = isChecked.value,
                                onCheckedChange = { checked ->
                                    isChecked.value = checked
                                    onIntent(TodoIntent.UpdateTodo(it.copy(isDone = checked)))
                                },
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = title.value,
                onValueChange = { title.value = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions =
                    KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
            )
            Button(
                onClick = {
                    onIntent(TodoIntent.InsertTodo(Todo(title = title.value, isDone = false)))
                    title.value = ""
                }
            ) {
                Text(text = "Save todo")
            }
        }
    }
}
