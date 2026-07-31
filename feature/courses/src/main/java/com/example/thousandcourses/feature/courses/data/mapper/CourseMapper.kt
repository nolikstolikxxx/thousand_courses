package com.example.thousandcourses.feature.courses.data.mapper

import com.example.thousandcourses.core.database.entity.CourseEntity
import com.example.thousandcourses.feature.courses.data.model.CourseDto


fun CourseDto.toEntity(): CourseEntity {

    return CourseEntity(

        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate

    )
}


fun CourseEntity.toDto(): CourseDto {

    return CourseDto(

        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate

    )
}