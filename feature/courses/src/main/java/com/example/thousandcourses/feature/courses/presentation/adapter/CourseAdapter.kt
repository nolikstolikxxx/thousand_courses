package com.example.thousandcourses.feature.courses.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thousandcourses.feature.courses.databinding.ItemCourseBinding
import com.example.thousandcourses.feature.courses.presentation.model.CourseUiModel


class CourseAdapter :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {


    private var courses: List<CourseUiModel> = emptyList()


    fun submitList(
        newCourses: List<CourseUiModel>
    ) {

        courses = newCourses
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {

        val binding =
            ItemCourseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return CourseViewHolder(binding)

    }


    override fun onBindViewHolder(
        holder: CourseViewHolder,
        position: Int
    ) {

        holder.bind(
            courses[position]
        )

    }


    override fun getItemCount(): Int =
        courses.size



    class CourseViewHolder(
        private val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            course: CourseUiModel
        ) {

            binding.courseTitleTextView.text =
                course.title

            binding.courseDescriptionTextView.text =
                course.description

            binding.courseInfoTextView.text =
                "Цена: ${course.price} ⭐ ${course.rating}"

        }

    }

}