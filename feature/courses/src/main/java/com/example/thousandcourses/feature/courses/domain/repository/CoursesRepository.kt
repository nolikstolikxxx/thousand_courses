package com.example.thousandcourses.feature.courses.domain.repository

import com.example.thousandcourses.feature.courses.data.model.CourseDto
import kotlinx.coroutines.flow.Flow


interface CoursesRepository {

    fun observeCourses(): Flow<List<CourseDto>>

    suspend fun getCourses(): List<CourseDto>

    suspend fun toggleFavorite(
        courseId: Int
    )
}