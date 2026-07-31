package com.example.thousandcourses.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.thousandcourses.core.database.entity.CourseEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CourseDao {


    @Query(
        "SELECT * FROM courses"
    )
    fun observeCourses():
            Flow<List<CourseEntity>>


    @Upsert
    suspend fun insertCourses(
        courses: List<CourseEntity>
    )


    @Upsert
    suspend fun updateCourse(
        course: CourseEntity
    )

    @Query(
        "SELECT * FROM courses WHERE id = :courseId"
    )
    suspend fun getCourseById(
        courseId: Int
    ): CourseEntity?
}