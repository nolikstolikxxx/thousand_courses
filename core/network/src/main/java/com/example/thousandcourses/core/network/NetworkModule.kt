package com.example.thousandcourses.core.network

import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single<Retrofit> {

        RetrofitProvider.create(
            baseUrl = "https://drive.usercontent.google.com/"
        )

    }

}