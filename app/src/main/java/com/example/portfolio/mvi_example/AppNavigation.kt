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
import com.example.portfolio.mvi_example.feature.drag_and_drop.DragAndDropSimpleScreen
import com.example.portfolio.mvi_example.feature.home.presentation.HomeCore
import com.example.portfolio.mvi_example.feature.home.presentation.HomeItem
import com.example.portfolio.mvi_example.feature.login.presentation.LoginCore
import com.example.portfolio.mvi_example.feature.lottie.LottieAnimationScreen
import com.example.portfolio.mvi_example.feature.mvi.view.TodoCore
import com.example.portfolio.mvi_example.feature.post.presentation.PostScreen
import com.example.portfolio.mvi_example.ui.theme.Route
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val rootBackStack =
        rememberNavBackStack(
            configuration =
                SavedStateConfiguration {
                    serializersModule = SerializersModule {
                        polymorphic(NavKey::class) {
                            subclass(Route.Home::class, Route.Home.serializer())
                            subclass(Route.Post::class, Route.Post.serializer())
                            subclass(Route.Login::class, Route.Login.serializer())
                            subclass(
                                Route.DragAndDropSimple::class,
                                Route.DragAndDropSimple.serializer(),
                            )
                        }
                    }
                },
            Route.Home,
        )

    NavDisplay(
        backStack = rootBackStack,
        modifier = modifier,
        onBack = { rootBackStack.removeLastOrNull() },
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider =
            entryProvider {
                entry<Route.Home> {
                    HomeCore(modifier = Modifier.fillMaxSize()) { rootBackStack.add(it) }
                }
                entry<Route.Post> {
                    PostScreen(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp))
                }
                entry<Route.Login> {
                    LoginCore(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                        rootBackStack.removeLastOrNull()
                    }
                }
                entry<Route.DragAndDropSimple> { DragAndDropSimpleScreen() }
                entry<Route.TodoIntents> { TodoCore(modifier = Modifier.fillMaxSize()) }
                entry<Route.Lottie> { LottieAnimationScreen(modifier = modifier) }
            },
        transitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                slideOutHorizontally(targetOffsetX = { -it }),
            )
        },
        popTransitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { -it }),
                slideOutHorizontally(targetOffsetX = { it }),
            )
        },
    )
}

fun itemsMenu(): ImmutableList<HomeItem> =
    persistentListOf(
        HomeItem(
            1,
            "Example MVI - Login",
            "Use MVI manually, with stateFlow and channel.",
            Route.Login,
        ),
        HomeItem(2, "Example MVI - View Post", "Use MVI framework orbit MVI", Route.Post),
        HomeItem(3, "Example MVI - View Todo", "Use MVI another example", Route.TodoIntents),
        HomeItem(4, "Drag And Drop Simple", "Drag And Drop Simple", Route.DragAndDropSimple),
        HomeItem(5, "Lottie Animation", "Lottie Animation", Route.Lottie),
    )
