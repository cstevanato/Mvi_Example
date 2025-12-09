package com.example.portfolio.mvi_example.feature.post.data

import com.example.portfolio.mvi_example.feature.post.domain.model.Post

interface PostApi {
    suspend fun getPosts(): List<Post>
}