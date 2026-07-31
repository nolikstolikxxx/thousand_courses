package com.example.thousandcourses.feature.courses.data.repository


import com.example.thousandcourses.core.database.dao.CourseDao
import com.example.thousandcourses.feature.courses.data.mapper.toDto
import com.example.thousandcourses.feature.courses.data.mapper.toEntity
import com.example.thousandcourses.feature.courses.data.model.CourseDto
import com.example.thousandcourses.feature.courses.data.remote.CoursesApi
import com.example.thousandcourses.feature.courses.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CoursesRepositoryImpl(
    private val api: CoursesApi ,
    private val dao: CourseDao
) : CoursesRepository {


    override fun observeCourses():
            Flow<List<CourseDto>> =
        dao.observeCourses()
            .map { courses ->
                courses.map {
                    it.toDto()
                }
            }


    override suspend fun getCourses(): List<CourseDto> {

        val response =
            api.getCourses(
                fileId = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"
            )

        dao.insertCourses(
            response.courses.map {
                it.toEntity()
            }
        )

        return response.courses
    }


    override suspend fun toggleFavorite(
        courseId: Int
    ) {

        val course =
            dao.getCourseById(courseId)
                ?: return

        dao.updateCourse(
            course.copy(
                hasLike = !course.hasLike
            )
        )
    }
}