package com.example.portfolio.mvi_example.feature.home.presentation

import com.example.portfolio.mvi_example.ui.theme.Route

sealed interface  HomeEvent {
    data class NavigateTo(val route: Route) : HomeEvent
}
