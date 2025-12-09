package com.example.portfolio.mvi_example.feature.post.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.portfolio.mvi_example.feature.post.domain.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val _getPosts: GetPostsUseCase,
) : ViewModel(), ContainerHost<PostState, UIComponent> {

    override val container: Container<PostState, UIComponent> = container(PostState())

    init {
        getPosts()
    }

    fun getPosts() {
        intent {
            val posts = _getPosts()
            posts.onEach { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        reduce {
                            state.copy(
                                progressBar = dataState.isLoading
                            )
                        }
                    }

                    is DataState.Success -> {
                        reduce {
                            state.copy(
                                posts = dataState.data
                            )
                        }
                    }

                    is DataState.Error -> {
                        when (dataState.uiComponent) {
                            is UIComponent.Toast -> {
                                postSideEffect(UIComponent.Toast(dataState.uiComponent.message))
                            }
                            else -> {}
                        }

                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}