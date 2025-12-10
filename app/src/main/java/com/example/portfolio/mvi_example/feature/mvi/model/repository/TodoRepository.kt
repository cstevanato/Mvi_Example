package com.example.portfolio.mvi_example.feature.mvi.model.repository

import com.example.portfolio.mvi_example.feature.mvi.model.loca.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun insert(todo: Todo)
    suspend fun delete(todo: Todo)
    suspend fun deleteAll()
    fun getAll(): Flow<List<Todo>>
    suspend fun update(todo: Todo)
}