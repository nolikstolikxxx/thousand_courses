package com.example.thousandcourses

import android.app.Application
import com.example.thousandcourses.core.network.networkModule
import com.example.thousandcourses.di.appModule
import com.example.thousandcourses.di.databaseModule
import com.example.thousandcourses.feature.courses.di.coursesModule
import com.example.thousandcourses.feature.login.di.loginModule
import com.example.thousandcourses.feature.favorites.di.favoritesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ThousandCoursesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@ThousandCoursesApp)

            modules(
                appModule,
                networkModule,
                databaseModule,
                loginModule,
                coursesModule,
                favoritesModule
            )
        }
    }
}