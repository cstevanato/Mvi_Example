package com.example.portfolio.mvi_example.feature.cepMvi.domain.repository

import com.example.portfolio.mvi_example.feature.cepMvi.domain.model.Address
import kotlinx.coroutines.flow.Flow

fun interface CepRepository {
    fun getCep(cep: String): Flow<Address>
}

