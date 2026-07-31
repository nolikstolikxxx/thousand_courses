package com.example.thousandcourses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import android.util.Log
import androidx.core.os.bundleOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment)
                    as NavHostFragment


        navHostFragment
            .childFragmentManager
            .setFragmentResultListener(
                "login_request",
                this
            ) { _, bundle ->

                val success =
                    bundle.getBoolean("success")

                Log.d(
                    "MainActivity",
                    "Login result received: $success"
                )

                if (success) {

                    navHostFragment
                        .navController
                        .navigate(
                            R.id.action_login_to_main
                        )
                }
            }

        navHostFragment
            .childFragmentManager
            .setFragmentResultListener(
                "course_request",
                this
            ) { _, bundle ->

                val courseId =
                    bundle.getInt("courseId")

                Log.d(
                    "MainActivity",
                    "Course selected: $courseId"
                )

                navHostFragment
                    .navController
                    .navigate(
                        R.id.courseDetailFragment,
                        bundleOf(
                            "courseId" to courseId
                        )
                    )
            }
    }
}