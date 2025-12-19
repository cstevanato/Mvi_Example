package com.example.portfolio.mvi_example.feature.mvi.model.di

import com.example.portfolio.mvi_example.feature.database.AppDataBase
import com.example.portfolio.mvi_example.feature.mvi.model.loca.TodoDao
import com.example.portfolio.mvi_example.feature.mvi.model.repository.TodoRepository
import com.example.portfolio.mvi_example.feature.mvi.model.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object TodoModelModule {

    @Provides
    fun provideTodoDao(todoDatabase: AppDataBase): TodoDao {
        return todoDatabase.todoDao()
    }

    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(todoDao)
    }
}
