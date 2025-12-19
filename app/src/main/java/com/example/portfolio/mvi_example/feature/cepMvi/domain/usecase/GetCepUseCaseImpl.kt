package com.example.portfolio.mvi_example.feature.cepMvi.domain.usecase

import com.example.portfolio.mvi_example.feature.cepMvi.domain.model.Address
import com.example.portfolio.mvi_example.feature.cepMvi.domain.repository.CepRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetCepUseCaseImpl @Inject constructor(private val cepRepository: CepRepository) :
    GetCepUseCase {

    override suspend fun invoke(cep: String): Flow<Result<Address>> {
        return cepRepository
            .getCep(cep)
            .map { Result.success(it) }
            .catch { emit(Result.failure(it)) }
    }
}
