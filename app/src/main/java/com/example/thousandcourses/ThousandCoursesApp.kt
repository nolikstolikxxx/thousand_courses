package com.example.thousandcourses

import android.app.Application
import com.example.thousandcourses.di.appModule
import com.example.thousandcourses.feature.login.di.loginModule
import org.koin.core.context.startKoin
import com.example.thousandcourses.feature.courses.di.coursesModule
import com.example.thousandcourses.core.network.networkModule

class ThousandCoursesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule,
                networkModule,
                loginModule,
                coursesModule
            )
        }
    }
}