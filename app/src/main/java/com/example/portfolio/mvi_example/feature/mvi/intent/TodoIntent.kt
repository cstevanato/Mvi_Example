package com.example.portfolio.mvi_example.feature.mvi.intent

import com.example.portfolio.mvi_example.feature.mvi.model.loca.Todo

sealed class TodoIntent {
    data class InsertTodo(val todo: Todo) : TodoIntent()

    data class UpdateTodo(val todo: Todo) : TodoIntent()

    data class DeleteTodo(val todo: Todo) : TodoIntent()
}
