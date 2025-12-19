package com.example.portfolio.mvi_example.feature.cepMvi.domain.usecase

import com.example.portfolio.mvi_example.feature.cepMvi.domain.model.Address
import kotlinx.coroutines.flow.Flow

interface GetCepUseCase {
    suspend operator fun invoke(cep: String): Flow<Result<Address>>
}

