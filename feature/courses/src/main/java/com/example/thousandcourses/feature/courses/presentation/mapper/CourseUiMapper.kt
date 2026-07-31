package com.example.thousandcourses.feature.courses.presentation.mapper

import com.example.thousandcourses.feature.courses.data.model.CourseDto
import com.example.thousandcourses.feature.courses.presentation.model.CourseUiModel


fun CourseDto.toUiModel(): CourseUiModel {

    return CourseUiModel(
        id = id,
        title = title,
        description = text,
        price = price,
        rating = rate,
        publishDate = publishDate,
        isLiked = hasLike
    )
}