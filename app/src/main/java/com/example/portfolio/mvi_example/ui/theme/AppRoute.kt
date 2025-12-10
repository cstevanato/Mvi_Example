package com.example.portfolio.mvi_example.ui.theme

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Home : Route, NavKey

    @Serializable
    data object Post : Route, NavKey

    @Serializable
    data object Login : Route, NavKey

    @Serializable
    data object DragAndDropSimple : Route

    @Serializable
    data object TodoIntents : Route
}