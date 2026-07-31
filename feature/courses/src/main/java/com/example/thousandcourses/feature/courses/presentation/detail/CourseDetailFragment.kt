package com.example.thousandcourses.feature.courses.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.thousandcourses.feature.courses.R
import com.example.thousandcourses.feature.courses.databinding.FragmentCourseDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseDetailFragment :
    Fragment(R.layout.fragment_course_detail) {


    private var _binding: FragmentCourseDetailBinding? = null

    private val binding: FragmentCourseDetailBinding
        get() = _binding!!

    private val viewModel: CourseDetailViewModel by viewModel()


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        _binding =
            FragmentCourseDetailBinding.bind(view)


        val courseId =
            arguments?.getInt("courseId")


        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.uiState.collect { state ->

                    val course =
                        state.course ?: return@collect

                    binding.courseDetailTitleTextView.text =
                        course.title

                    binding.courseDetailDescriptionTextView.text =
                        course.description

                    binding.courseDetailRatingTextView.text =
                        course.rating

                    binding.courseDetailPriceTextView.text =
                        course.price

                    binding.courseDetailDateTextView.text =
                        course.publishDate

                    binding.courseDetailImageView.setImageResource(
                        R.drawable.course_placeholder
                    )

                    binding.courseDetailFavoriteButton.setImageResource(
                        if (course.isLiked) {
                            R.drawable.ic_favorite_filled
                        } else {
                            R.drawable.ic_favorite_outline
                        }
                    )
                }

            }
        }


        courseId?.let {

            viewModel.loadCourse(it)

        }

        courseId?.let { id ->

            binding.courseDetailFavoriteButton.setOnClickListener {

                viewModel.onFavoriteClicked(
                    id
                )

            }

        }

        binding.enrollButton.setOnClickListener {

            // TODO: Open enrollment screen

        }
    }


    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null
    }
}