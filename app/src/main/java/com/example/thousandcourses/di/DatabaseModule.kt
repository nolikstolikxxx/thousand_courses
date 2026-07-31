package com.example.thousandcourses.di

import androidx.room.Room
import com.example.thousandcourses.core.database.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {


    single<AppDatabase> {

        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "courses_database"
        )
            .build()

    }


    single {

        get<AppDatabase>()
            .courseDao()

    }

}