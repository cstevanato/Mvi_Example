package com.example.portfolio.mvi_example.feature.cepMvi.ui

sealed class CepEvent {
    data class ShowToast(val message: String) : CepEvent()
}
