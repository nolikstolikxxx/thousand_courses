package com.example.thousandcourses.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thousandcourses.core.database.dao.CourseDao
import com.example.thousandcourses.core.database.entity.CourseEntity


@Database(
    entities = [
        CourseEntity::class
    ],
    version = 1
)
abstract class AppDatabase :
    RoomDatabase() {


    abstract fun courseDao():
            CourseDao
}