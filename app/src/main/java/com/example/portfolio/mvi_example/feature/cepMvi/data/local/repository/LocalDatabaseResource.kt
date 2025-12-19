package com.example.portfolio.mvi_example.feature.cepMvi.data.local.repository

import com.example.portfolio.mvi_example.feature.cepMvi.data.local.entiry.AddressEntity
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseResource {

    fun getCepEntity(name: String): Flow<AddressEntity>

    suspend fun saveCepEntity(cepEntity: AddressEntity)

}