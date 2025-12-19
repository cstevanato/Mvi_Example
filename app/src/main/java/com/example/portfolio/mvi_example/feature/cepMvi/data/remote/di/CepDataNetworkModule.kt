package com.example.portfolio.mvi_example.feature.cepMvi.data.remote.di

import com.example.portfolio.mvi_example.feature.cepMvi.data.remote.repository.RemoteDataSource
import com.example.portfolio.mvi_example.feature.cepMvi.data.remote.repository.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CepDataNetworkModule {

    @Provides
    @Singleton
    fun provideNetworkDataSource(
        httpClient: HttpClient
    ): RemoteDataSource {
        return RemoteDataSourceImpl(httpClient)
    }

}