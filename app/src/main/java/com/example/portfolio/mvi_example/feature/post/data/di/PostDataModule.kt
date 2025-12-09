package com.example.portfolio.mvi_example.feature.post.data.di

import com.example.portfolio.mvi_example.feature.post.data.PostApi
import com.example.portfolio.mvi_example.feature.post.data.PostApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostDataModule {
    @Provides
    @Singleton
    fun providerPostApi(
        httpClient: HttpClient
    ): PostApi = PostApiImpl(
        httpClient = httpClient
    )
}