package com.example.portfolio.mvi_example.feature.cepMvi.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.entiry.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CepDao {
    @Query("SELECT * FROM address WHERE replace(cep, '-', '') = :cep")
//    @Query("SELECT * FROM address WHERE cep = :cep")
    fun getCep(cep: String): Flow<AddressEntity>

    @Upsert
    suspend fun upsertCep(cep: AddressEntity)
}

