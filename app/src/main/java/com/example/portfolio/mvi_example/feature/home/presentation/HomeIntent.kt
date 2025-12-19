package com.example.portfolio.mvi_example.feature.home.presentation

import com.example.portfolio.mvi_example.ui.theme.Route

sealed interface HomeIntent {
    data object LoadItems : HomeIntent

    data class NavigateTo(val route: Route) : HomeIntent
}
