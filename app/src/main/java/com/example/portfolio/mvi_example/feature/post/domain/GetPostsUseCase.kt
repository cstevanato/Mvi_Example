package com.example.portfolio.mvi_example.feature.post.domain

import com.example.portfolio.mvi_example.feature.post.domain.model.Post
import com.example.portfolio.mvi_example.feature.post.presentation.DataState
import kotlinx.coroutines.flow.Flow

fun interface GetPostsUseCase {
    suspend operator fun invoke(): Flow<DataState<List<Post>>>
}
