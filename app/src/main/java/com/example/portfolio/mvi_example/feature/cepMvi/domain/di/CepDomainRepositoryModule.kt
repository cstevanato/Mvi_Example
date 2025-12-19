package com.example.portfolio.mvi_example.feature.cepMvi.domain.di

import com.example.portfolio.mvi_example.feature.cepMvi.data.repository.CepRepositoryImpl
import com.example.portfolio.mvi_example.feature.cepMvi.domain.repository.CepRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CepDomainRepositoryModule {

    @Binds abstract fun bindCepRepository(cepRepositoryImpl: CepRepositoryImpl): CepRepository
}
