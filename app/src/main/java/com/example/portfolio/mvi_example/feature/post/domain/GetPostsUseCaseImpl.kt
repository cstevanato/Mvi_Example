package com.example.portfolio.mvi_example.feature.post.domain

import com.example.portfolio.mvi_example.feature.post.domain.model.Post
import com.example.portfolio.mvi_example.feature.post.data.PostApi
import com.example.portfolio.mvi_example.feature.post.presentation.DataState
import com.example.portfolio.mvi_example.feature.post.presentation.UIComponent
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPostsUseCaseImpl @Inject constructor(
    private val postApi: PostApi
) : GetPostsUseCase {
    override suspend fun invoke(): Flow<DataState<List<Post>>> {
        return flow {
            emit(DataState.Loading(true))
            try {
                val posts = postApi.getPosts()
                emit(DataState.Success(posts))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataState.Error(UIComponent.Toast(e.message ?: "Unknown Error")))
            } finally {
                emit(DataState.Loading(false))
            }
        }
    }
}