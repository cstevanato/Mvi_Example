package com.example.portfolio.mvi_example.feature.database.di

import android.content.Context
import com.example.portfolio.mvi_example.feature.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideCepDataBase(@ApplicationContext context: Context): AppDataBase =
        AppDataBase.Companion.getInstance(context)
}
