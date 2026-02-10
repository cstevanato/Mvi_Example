package com.example.portfolio.mvi_example.feature.cepMvi.data.repository

import android.util.Log
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.entiry.toCep
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.repository.LocalDatabaseResource
import com.example.portfolio.mvi_example.feature.cepMvi.data.remote.model.toEntity
import com.example.portfolio.mvi_example.feature.cepMvi.data.remote.repository.RemoteDataSource
import com.example.portfolio.mvi_example.feature.cepMvi.domain.model.Address
import com.example.portfolio.mvi_example.feature.cepMvi.domain.repository.CepRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.take

const val TAG = "CepRepositoryImpl"

class CepRepositoryImpl
@Inject
constructor(
    private val localDatabaseResource: LocalDatabaseResource,
    private val remoteDataSource: RemoteDataSource,
) : CepRepository {
    override fun getCep(cep: String): Flow<Address> {
        return localDatabaseResource
            .getCepEntity(cep)
            .map { addressEntity ->
                Log.i(TAG, "Passei aqui 001")
                addressEntity.toCep()
            }
            .retryWhen { cause, attempt ->
                Log.i(TAG, "Passei aqui 002")
                if (cause is IllegalStateException && attempt < 1) {
                    Log.i(TAG, "Passei aqui 003")
                    fetchCep(cep)
                    Log.i(TAG, "Passei aqui 004")
                    true
                } else {
                    Log.i(TAG, "Passei aqui 005")
                    false
                }
            }
            .take(1)
    }

    private suspend fun fetchCep(cep: String): Address {
        return remoteDataSource
            .fetchRemoteCep(cep)
            .toEntity()
            .also { localDatabaseResource.saveCepEntity(it) }
            .toCep()
    }
}

/// outros exemplos para retry
//var attempt = 0
//flow {
//    if (attempt < 2) {
//        attempt++
//        throw RuntimeException("Network error attempt $attempt")
//    }
//    emit("Success!")
//}.retry(retries = 2) { cause ->
//    // Predicate: retry only on RuntimeException
//    cause is RuntimeException
//}.collect { value ->
//    println("Collected: $value")
//}
//// Output:
//// Collected: Success!
