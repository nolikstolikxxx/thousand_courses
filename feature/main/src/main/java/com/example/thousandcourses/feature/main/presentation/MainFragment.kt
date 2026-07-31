package com.example.thousandcourses.feature.main.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.thousandcourses.feature.main.R
import com.example.thousandcourses.feature.main.databinding.FragmentMainBinding

class MainFragment :
    Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null

    private val binding
        get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(
            view,
            savedInstanceState
        )

        _binding =
            FragmentMainBinding.bind(view)

        Log.d(
            "NAVIGATION_TEST",
            "MainFragment listener registered: $childFragmentManager"
        )

        childFragmentManager.setFragmentResultListener(
            "course_request",
            viewLifecycleOwner
        ) { _, bundle ->

            val courseId =
                bundle.getInt("courseId")


            Log.d(
                "NAVIGATION_TEST",
                "MainFragment received: $courseId"
            )


            parentFragmentManager.setFragmentResult(
                "course_request",
                bundle
            )

        }


        val navHostFragment =
            childFragmentManager.findFragmentById(
                R.id.main_nav_host
            ) as NavHostFragment

        navHostFragment
            .childFragmentManager
            .setFragmentResultListener(
                "course_request",
                viewLifecycleOwner
            ) { _, bundle ->

                val courseId =
                    bundle.getInt("courseId")


                Log.d(
                    "NAVIGATION_TEST",
                    "MainFragment received: $courseId"
                )


                parentFragmentManager.setFragmentResult(
                    "course_request",
                    bundle
                )
            }


        binding.bottomNavigationView.setupWithNavController(
            navHostFragment.navController
        )
    }

    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}