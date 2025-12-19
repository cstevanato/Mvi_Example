package com.example.portfolio.mvi_example.feature.home.presentation

import androidx.compose.runtime.Immutable
import com.example.portfolio.mvi_example.ui.theme.Route

@Immutable
data class HomeItem(val id: Int, val title: String, val description: String, val route: Route)
