package com.example.portfolio.mvi_example.feature.cepMvi.data.remote.repository

import com.example.portfolio.mvi_example.feature.cepMvi.data.remote.model.CepResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun fetchRemoteCep(cep: String): CepResponse {
        val client = httpClient
            .get("https://viacep.com.br/ws/$cep/json/")
        return client.body<CepResponse>()
    }
}