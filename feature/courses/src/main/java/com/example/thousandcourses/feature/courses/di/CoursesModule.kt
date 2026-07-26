package com.example.thousandcourses.feature.courses.di

import com.example.thousandcourses.feature.courses.data.remote.CoursesApi
import com.example.thousandcourses.feature.courses.data.repository.CoursesRepositoryImpl
import com.example.thousandcourses.feature.courses.domain.repository.CoursesRepository
import com.example.thousandcourses.feature.courses.presentation.CoursesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val coursesModule = module {

    single<CoursesApi> {

        get<Retrofit>()
            .create(CoursesApi::class.java)

    }


    single<CoursesRepository> {

        CoursesRepositoryImpl(
            api = get()
        )

    }


    viewModel {

        CoursesViewModel(
            repository = get()
        )

    }

}