package com.example.portfolio.mvi_example.feature.home.presentation

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeScreenState(
    val isLoading: Boolean = false,
    val items: ImmutableList<HomeItem> = persistentListOf(),
    val error: String? = null,
)
