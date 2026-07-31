package com.example.thousandcourses.feature.courses.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandcourses.feature.courses.databinding.ItemCourseBinding
import com.example.thousandcourses.feature.courses.presentation.model.CourseUiModel
import com.example.thousandcourses.feature.courses.R
import android.util.Log


class CourseAdapter(
    private val onCourseClick: (CourseUiModel) -> Unit,
    private val onFavoriteClick: (CourseUiModel) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {


    private var courses: List<CourseUiModel> = emptyList()


    fun submitList(
        newCourses: List<CourseUiModel>
    ) {

        courses = newCourses
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int
    ): CourseViewHolder {

        val binding =
            ItemCourseBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )

        return CourseViewHolder(
            binding = binding,
            onCourseClick = onCourseClick,
            onFavoriteClick = onFavoriteClick
        )

    }


    override fun onBindViewHolder(
        holder: CourseViewHolder ,
        position: Int
    ) {

        holder.bind(
            courses[position]
        )

    }


    override fun getItemCount(): Int =
        courses.size


    class CourseViewHolder(
        private val binding: ItemCourseBinding,
        private val onCourseClick: (CourseUiModel) -> Unit,
        private val onFavoriteClick: (CourseUiModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            course: CourseUiModel
        ) {
            Log.d(
                "ADAPTER_BIND",
                "${course.title}: ${course.isLiked}"
            )

            binding.courseTitleTextView.text =
                course.title

            binding.courseDescriptionTextView.text =
                course.description

            binding.ratingTextView.text =
                course.rating

            binding.dateTextView.text =
                course.publishDate

            binding.priceTextView.text =
                course.price

            binding.courseImageView.setImageResource(
                R.drawable.course_placeholder
            )

            binding.favoriteImageButton.setImageResource(

                if (course.isLiked) {
                    R.drawable.ic_favorite_filled

                } else {
                    R.drawable.ic_favorite_outline

                }

            )

            binding.root.setOnClickListener {

                onCourseClick(course)

            }

            binding.favoriteImageButton.setOnClickListener {

                onFavoriteClick(course)

            }

            binding.favoriteImageButton.setImageResource(
                if (course.isLiked) {
                    R.drawable.ic_favorite_filled
                } else {
                    R.drawable.ic_favorite_outline
                }
            )

        }

    }

}