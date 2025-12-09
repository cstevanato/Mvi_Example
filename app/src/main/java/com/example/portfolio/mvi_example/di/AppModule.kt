package com.example.portfolio.mvi_example.di

import com.example.portfolio.mvi_example.feature.post.domain.GetPostsUseCase
import com.example.portfolio.mvi_example.feature.post.domain.GetPostsUseCaseImpl
import com.example.portfolio.mvi_example.feature.post.data.PostApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    @ViewModelScoped
    fun bindGetPosts(postApi: PostApi): GetPostsUseCase = GetPostsUseCaseImpl(postApi = postApi)
}

