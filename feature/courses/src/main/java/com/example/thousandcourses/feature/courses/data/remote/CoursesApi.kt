package com.example.thousandcourses.feature.courses.data.remote

import com.example.thousandcourses.feature.courses.data.model.CoursesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CoursesApi {

    @GET("u/0/uc")
    suspend fun getCourses(
        @Query("id") fileId: String ,
        @Query("export") export: String = "download"
    ): CoursesResponse

}