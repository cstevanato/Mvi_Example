package com.example.portfolio.mvi_example.feature.post.presentation

sealed class DataState<T> {
    data class Loading<T>(val isLoading: Boolean) : DataState<T>()

    data class Success<T>(val data: T) : DataState<T>()

    data class Error<T>(val uiComponent: UIComponent) : DataState<T>()
}

sealed class UIComponent {
    data class Toast(val message: String) : UIComponent()

    data class Dialog(val title: String, val message: String) : UIComponent()

    data class SnackBar(val message: String) : UIComponent()
}
