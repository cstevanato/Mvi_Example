package com.example.portfolio.mvi_example.feature.cepMvi.ui

sealed class CepIntent {
    data class SearchAddress(val cep: String) : CepIntent()
}
