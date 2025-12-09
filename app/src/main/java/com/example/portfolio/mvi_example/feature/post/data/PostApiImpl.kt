package com.example.portfolio.mvi_example.feature.post.data

import com.example.portfolio.mvi_example.feature.post.domain.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import jakarta.inject.Inject
import kotlinx.coroutines.delay

class PostApiImpl @Inject constructor(
    private val httpClient: HttpClient
) : PostApi {
    override suspend fun getPosts(): List<Post> {
        delay(2000) // only for demo
        val response = httpClient
            .get("https://jsonplaceholder.typicode.com/posts")
            .body<List<Post>>()

        return response
    }
}