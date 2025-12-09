package com.example.portfolio.mvi_example.feature.post.presentation

import com.example.portfolio.mvi_example.feature.post.domain.model.Post

data class PostState (
    val progressBar: Boolean = false,
    val posts: List<Post> = emptyList(),
)