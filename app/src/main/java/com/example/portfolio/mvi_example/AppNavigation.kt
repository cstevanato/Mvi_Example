package com.example.portfolio.mvi_example

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.portfolio.mvi_example.feature.login.presentation.LoginCore
import com.example.portfolio.mvi_example.feature.home.presentation.HomeCore
import com.example.portfolio.mvi_example.feature.post.presentation.PostScreen
import com.example.portfolio.mvi_example.ui.theme.Route
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.Home::class, Route.Home.serializer())
                    subclass(Route.Post::class, Route.Post.serializer())
                    subclass(Route.Login::class, Route.Login.serializer())
                }
            }
        },
        Route.Home
    )

    NavDisplay(
        backStack = rootBackStack,
        modifier = modifier,
        onBack = { rootBackStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Home> {
                HomeCore(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    rootBackStack.add(it)
                }
            }
            entry<Route.Post> {
                PostScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                )
            }
            entry<Route.Login> {
                LoginCore(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    rootBackStack.removeLastOrNull()
                }
            }
        },
        transitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                slideOutHorizontally(targetOffsetX = { -it })
            )
        },
        popTransitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { -it }),
                slideOutHorizontally(targetOffsetX = { it })
            )
        }
    )
}