package com.example.thousandcourses.feature.courses.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thousandcourses.feature.courses.R
import com.example.thousandcourses.feature.courses.databinding.FragmentCoursesBinding
import com.example.thousandcourses.feature.courses.presentation.adapter.CourseAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.view.isVisible

class CoursesFragment :
    Fragment(R.layout.fragment_courses) {

    private var _binding: FragmentCoursesBinding? = null

    private val binding: FragmentCoursesBinding
        get() = _binding!!

    private val viewModel: CoursesViewModel by viewModel()

    private val adapter = CourseAdapter()


    override fun onViewCreated(
        view: View ,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view , savedInstanceState)
        _binding = FragmentCoursesBinding.bind(view)

        binding.coursesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.coursesRecyclerView.adapter =
            adapter

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.uiState.collect { state ->

                    binding.loadingProgressBar.isVisible =
                        state.isLoading


                    binding.errorTextView.isVisible =
                        state.errorMessage != null


                    binding.coursesRecyclerView.isVisible =
                        state.courses.isNotEmpty()


                    adapter.submitList(
                        state.courses
                    )

                }
            }
        }

        lifecycleScope.launch {

            viewModel.uiState.collect { state ->
                Log.d(
                    "CoursesFragment" ,
                    "Courses loaded: ${state.courses.size}"
                )
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}