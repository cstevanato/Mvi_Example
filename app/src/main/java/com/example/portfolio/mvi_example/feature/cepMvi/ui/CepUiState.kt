package com.example.portfolio.mvi_example.feature.cepMvi.ui

import com.example.portfolio.mvi_example.feature.cepMvi.domain.model.Address

data class CepUiState(val address: Address? = null, val isLoading: Boolean = false)
