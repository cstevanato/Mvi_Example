package com.example.portfolio.mvi_example.feature.cepMvi.data.di

import com.example.portfolio.mvi_example.feature.database.AppDataBase
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.dao.CepDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CepDataDaoModule {

    @Singleton
    @Provides
    fun provideCepDao(appDataBase: AppDataBase): CepDao = appDataBase.cepDao()
}