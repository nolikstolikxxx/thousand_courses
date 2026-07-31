package com.example.thousandcourses.feature.courses.presentation.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thousandcourses.feature.courses.domain.repository.CoursesRepository
import com.example.thousandcourses.feature.courses.presentation.mapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CourseDetailViewModel(
    private val repository: CoursesRepository
) : ViewModel() {


    private val _uiState =
        MutableStateFlow(
            CourseDetailUiState()
        )


    val uiState: StateFlow<CourseDetailUiState> =
        _uiState.asStateFlow()



    fun loadCourse(
        courseId: Int
    ) {

        viewModelScope.launch {

            _uiState.value =
                CourseDetailUiState(
                    isLoading = true
                )


            try {

                repository.getCourses()

                repository
                    .observeCourses()
                    .collect { courses ->

                        val course =
                            courses
                                .firstOrNull {
                                    it.id == courseId
                                }
                                ?.toUiModel()

                        _uiState.value =
                            CourseDetailUiState(
                                course = course
                            )
                    }


            } catch (exception: Exception) {

                _uiState.value =
                    CourseDetailUiState(
                        errorMessage =
                            exception.message
                    )
            }
        }
    }


    fun onFavoriteClicked(
        courseId: Int
    ) {

        viewModelScope.launch {

            repository.toggleFavorite(
                courseId
            )

        }
    }
}