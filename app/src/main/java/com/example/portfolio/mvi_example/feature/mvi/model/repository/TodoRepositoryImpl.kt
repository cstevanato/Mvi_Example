package com.example.portfolio.mvi_example.feature.mvi.model.repository

import com.example.portfolio.mvi_example.feature.mvi.model.loca.Todo
import com.example.portfolio.mvi_example.feature.mvi.model.loca.TodoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {
    override suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    override suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }

    override suspend fun deleteAll() {
        todoDao.deleteAll()
    }

    override fun getAll(): Flow<List<Todo>> {
        return todoDao.getAll()
    }

    override suspend fun update(todo: Todo) {
        todoDao.update(todo)
    }
}