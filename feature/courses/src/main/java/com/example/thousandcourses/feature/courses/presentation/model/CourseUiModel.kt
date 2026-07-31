package com.example.thousandcourses.feature.courses.presentation.model

data class CourseUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    val publishDate: String,
    val isLiked: Boolean
)