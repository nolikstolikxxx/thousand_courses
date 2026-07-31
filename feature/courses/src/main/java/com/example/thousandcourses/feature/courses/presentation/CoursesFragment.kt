package com.example.thousandcourses.feature.courses.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
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
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController


class CoursesFragment :
    Fragment(R.layout.fragment_courses) {

    private var _binding: FragmentCoursesBinding? = null

    private val binding: FragmentCoursesBinding
        get() = _binding!!

    private val viewModel: CoursesViewModel by viewModel()

    private val adapter = CourseAdapter(

        onCourseClick = { course ->

            Log.d(
                "NAVIGATION_TEST",
                "Course clicked: ${course.id}"
            )

            parentFragmentManager.setFragmentResult(
                "course_request",
                bundleOf(
                    "courseId" to course.id
                )
            )

            Log.d(
                "NAVIGATION_TEST",
                "Result sent from: $parentFragmentManager"
            )

        } ,

        onFavoriteClick = { course ->

            viewModel.onFavoriteClicked(
                course.id
            )

        }

    )


    override fun onViewCreated(
        view: View ,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view , savedInstanceState)
        _binding = FragmentCoursesBinding.bind(view)

        binding.searchEditText.doOnTextChanged { text , _ , _ , _ ->

            viewModel.onSearchQueryChanged(
                text.toString()
            )
        }

        binding.coursesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.coursesRecyclerView.adapter = adapter

        binding.filterButton.setOnClickListener { view ->

            val popupMenu = PopupMenu(requireContext() , view)

            popupMenu.menu.add(getString(R.string.sort_by_title))
            popupMenu.menu.add(getString(R.string.sort_by_rating))
            popupMenu.menu.add(getString(R.string.sort_by_publish_date))

            popupMenu.setOnMenuItemClickListener { menuItem ->

                when (menuItem.title) {

                    getString(R.string.sort_by_title) -> {
                        viewModel.sortCourses(
                            CourseSortType.TITLE
                        )
                    }

                    getString(R.string.sort_by_rating) -> {
                        viewModel.sortCourses(
                            CourseSortType.RATING
                        )
                    }

                    getString(R.string.sort_by_publish_date) -> {
                        viewModel.sortCourses(
                            CourseSortType.PUBLISH_DATE
                        )
                    }
                }
                true
            }
            popupMenu.show()
        }

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

                    Log.d(
                        "FAVORITE_UI" ,
                        state.courses.joinToString {
                            "${it.title}: ${it.isLiked}"
                        }
                    )

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