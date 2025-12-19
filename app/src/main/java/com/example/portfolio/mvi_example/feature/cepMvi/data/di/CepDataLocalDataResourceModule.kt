package com.example.portfolio.mvi_example.feature.cepMvi.data.di

import com.example.portfolio.mvi_example.feature.cepMvi.data.local.repository.LocalDatabaseResource
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.repository.LocalDatabaseResourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CepDataLocalDataResourceModule {

    @Binds
    abstract fun bindCepLocalResource(
        cepLocalDataBaseImpl: LocalDatabaseResourceImpl
    ): LocalDatabaseResource
}
