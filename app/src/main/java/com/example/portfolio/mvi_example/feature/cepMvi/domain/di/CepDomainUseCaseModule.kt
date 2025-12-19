package com.example.portfolio.mvi_example.feature.cepMvi.domain.di

import com.example.portfolio.mvi_example.feature.cepMvi.domain.repository.CepRepository
import com.example.portfolio.mvi_example.feature.cepMvi.domain.usecase.GetCepUseCase
import com.example.portfolio.mvi_example.feature.cepMvi.domain.usecase.GetCepUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CepDomainUseCaseModule {

    @Provides
    @ViewModelScoped
    fun bindGetCepUseCase(cepRepository: CepRepository): GetCepUseCase =
        GetCepUseCaseImpl(cepRepository)
}
