package com.example.portfolio.mvi_example.feature.mvi.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.portfolio.mvi_example.feature.mvi.intent.TodoIntent
import com.example.portfolio.mvi_example.feature.mvi.model.loca.Todo
import com.example.portfolio.mvi_example.feature.mvi.model.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {


    fun sendIntent(intent: TodoIntent) {
        when(intent) {
            is TodoIntent.InsertTodo -> insertTodo(intent.todo)
            is TodoIntent.DeleteTodo -> deleteTodo(intent.todo)
            is TodoIntent.UpdateTodo -> updateTodo(intent.todo)

        }

    }

    private fun insertTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insert(todo)
        }
    }

   private fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.delete(todo)
        }
    }

    private fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.update(todo)
        }
    }

    fun getAllTodos(): Flow<List<Todo>> {
        return todoRepository.getAll()
    }
}