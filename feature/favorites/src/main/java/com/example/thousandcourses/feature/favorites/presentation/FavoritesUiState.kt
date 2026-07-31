package com.example.thousandcourses.feature.favorites.presentation

import com.example.thousandcourses.feature.courses.presentation.model.CourseUiModel

data class FavoritesUiState(

    val courses: List<CourseUiModel> = emptyList(),

    val isLoading: Boolean = false,

    val errorMessage: String? = null

)