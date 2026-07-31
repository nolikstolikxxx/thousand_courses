package com.example.thousandcourses.feature.courses.presentation.detail

import com.example.thousandcourses.feature.courses.presentation.model.CourseUiModel

data class CourseDetailUiState(
    val course: CourseUiModel? = null ,
    val isLoading: Boolean = false ,
    val errorMessage: String? = null
)