package com.example.thousandcourses.feature.courses.presentation

import com.example.thousandcourses.feature.courses.presentation.model.CourseUiModel

data class CoursesUiState(
    val courses: List<CourseUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)