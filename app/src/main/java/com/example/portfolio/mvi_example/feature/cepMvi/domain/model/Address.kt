package com.example.portfolio.mvi_example.feature.cepMvi.domain.model

data class Address(
    val cep: String?,
    val logradouro: String?,
    val complemento: String?,
    val unidade: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?,
    val estado: String?,
    val regiao: String?,
    val ddd: String?,
)
