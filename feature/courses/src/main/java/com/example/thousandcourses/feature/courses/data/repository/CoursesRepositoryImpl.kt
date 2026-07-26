package com.example.thousandcourses.feature.courses.data.repository

import com.example.thousandcourses.feature.courses.data.model.CourseDto
import com.example.thousandcourses.feature.courses.data.remote.CoursesApi
import com.example.thousandcourses.feature.courses.domain.repository.CoursesRepository

class CoursesRepositoryImpl(
    private val api: CoursesApi
) : CoursesRepository {

    override suspend fun getCourses(): List<CourseDto> {

        return api.getCourses(
            fileId = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"
        ).courses

    }
}