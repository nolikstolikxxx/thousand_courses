package com.example.thousandcourses.feature.favorites.presentation


import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thousandcourses.feature.courses.presentation.adapter.CourseAdapter
import com.example.thousandcourses.feature.favorites.R
import com.example.thousandcourses.feature.favorites.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.util.Log
import androidx.core.os.bundleOf


class FavoritesFragment :
    Fragment(R.layout.fragment_favorites) {


    private var _binding: FragmentFavoritesBinding? = null


    private val binding: FragmentFavoritesBinding
        get() = _binding!!


    private val viewModel: FavoritesViewModel by viewModel()


    private val adapter =
        CourseAdapter(

            onCourseClick = { course ->

                Log.d(
                    "NAVIGATION_TEST",
                    "Favorite course clicked: ${course.id}"
                )

                parentFragmentManager.setFragmentResult(
                    "course_request",
                    bundleOf(
                        "courseId" to course.id
                    )
                )

            },

            onFavoriteClick = { course ->

                viewModel.removeFavorite(
                    course.id
                )

            }

        )



    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)


        _binding =
            FragmentFavoritesBinding.bind(view)


        binding.favoritesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())


        binding.favoritesRecyclerView.adapter =
            adapter



        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {


                viewModel.uiState.collect { state ->


                    adapter.submitList(
                        state.courses
                    )


                    binding.emptyFavoritesTextView.isVisible =
                        state.courses.isEmpty()


                }

            }

        }

    }



    override fun onDestroyView() {

        super.onDestroyView()

        _binding = null

    }

}