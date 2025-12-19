package com.example.portfolio.mvi_example.feature.cepMvi.data.remote.repository

import com.example.portfolio.mvi_example.feature.cepMvi.data.remote.model.CepResponse

interface RemoteDataSource {
    suspend fun fetchRemoteCep(cep: String): CepResponse
}
