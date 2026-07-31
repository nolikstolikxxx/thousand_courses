package com.example.thousandcourses.feature.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thousandcourses.feature.courses.domain.repository.CoursesRepository
import com.example.thousandcourses.feature.courses.presentation.mapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class FavoritesViewModel(
    private val repository: CoursesRepository
) : ViewModel() {


    private val _uiState =
        MutableStateFlow(
            FavoritesUiState(
                isLoading = true
            )
        )


    val uiState: StateFlow<FavoritesUiState> =
        _uiState.asStateFlow()


    init {

        observeFavorites()

    }


    private fun observeFavorites() {

        viewModelScope.launch {


            repository.observeCourses()
                .map { courses ->

                    courses
                        .filter {
                            it.hasLike
                        }
                        .map {
                            it.toUiModel()
                        }

                }
                .collect { favoriteCourses ->


                    _uiState.value =
                        FavoritesUiState(
                            courses = favoriteCourses
                        )

                }

        }

    }

    fun removeFavorite(
        courseId: Int
    ) {

        viewModelScope.launch {

            repository.toggleFavorite(
                courseId = courseId
            )

        }

    }
}