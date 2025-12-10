package com.example.portfolio.mvi_example.feature.mvi.model.di

import android.content.Context
import com.example.portfolio.mvi_example.feature.mvi.model.loca.TodoDao
import com.example.portfolio.mvi_example.feature.mvi.model.loca.TodoDatabase
import com.example.portfolio.mvi_example.feature.mvi.model.repository.TodoRepository
import com.example.portfolio.mvi_example.feature.mvi.model.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TodoModelModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return TodoDatabase.getInstance(context)
    }

    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao()
    }

    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(todoDao)
    }
}