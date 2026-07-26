package com.example.thousandcourses.feature.courses.domain.repository

import com.example.thousandcourses.feature.courses.data.model.CourseDto

interface CoursesRepository {

    suspend fun getCourses(): List<CourseDto>

}