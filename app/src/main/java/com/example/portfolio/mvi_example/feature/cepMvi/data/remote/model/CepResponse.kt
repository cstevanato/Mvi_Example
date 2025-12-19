package com.example.portfolio.mvi_example.feature.cepMvi.data.remote.model

import com.example.portfolio.mvi_example.feature.cepMvi.data.local.entiry.AddressEntity
import kotlinx.serialization.Serializable

@Serializable
data class CepResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val unidade: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val estado: String,
    val regiao: String,
    val ddd: String,
)

fun CepResponse.toEntity() = AddressEntity(
    cep = cep,
    logradouro = logradouro,
    complemento = complemento,
    unidade = unidade,
    bairro = bairro,
    localidade = localidade,
    uf = uf,
    estado = estado,
    regiao = regiao,
    ddd = ddd,
)