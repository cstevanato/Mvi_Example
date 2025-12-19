package com.example.portfolio.mvi_example.feature.cepMvi.data.local.repository

import com.example.portfolio.mvi_example.core.di.IoDispatcher
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.dao.CepDao
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.entiry.AddressEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDatabaseResourceImpl @Inject constructor(
    private val dao: CepDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : LocalDatabaseResource {
    override fun getCepEntity(name: String): Flow<AddressEntity> {
        return dao.getCep(name)
    }

    override suspend fun saveCepEntity(cepEntity: AddressEntity) {
        withContext(dispatcher) {
            dao.upsertCep(cepEntity)
        }
    }

}