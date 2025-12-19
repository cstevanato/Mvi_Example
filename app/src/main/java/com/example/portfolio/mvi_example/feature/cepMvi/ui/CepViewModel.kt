package com.example.portfolio.mvi_example.feature.cepMvi.ui

import androidx.lifecycle.viewModelScope
import com.example.portfolio.mvi_example.feature.cepMvi.domain.usecase.GetCepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CepViewModel @Inject constructor(private val getCepUseCase: GetCepUseCase) :
    BaseViewModel<CepUiState, CepEvent, CepIntent>() {
    override fun createInitialState(): CepUiState {
        return CepUiState()
    }

    override fun handleIntent(intent: CepIntent) {
        when (intent) {
            is CepIntent.SearchAddress -> fetchCep(intent.cep)
        }
    }

    private fun fetchCep(cep: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            getCepUseCase(cep).collect {
                it.onSuccess { cep -> setState { copy(address = cep, isLoading = false) } }
                    .onFailure {
                        setState { copy(isLoading = false) }
                        setEvent { CepEvent.ShowToast(it.message.toString()) }
                    }
            }
        }
    }
}
