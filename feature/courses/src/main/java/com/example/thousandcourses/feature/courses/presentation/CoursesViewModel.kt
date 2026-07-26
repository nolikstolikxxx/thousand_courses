package com.example.thousandcourses.feature.courses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thousandcourses.feature.courses.domain.repository.CoursesRepository
import com.example.thousandcourses.feature.courses.presentation.mapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val repository: CoursesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoursesUiState())

    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()


    init {
        loadCourses()
    }


    private fun loadCourses() {

        viewModelScope.launch {

            _uiState.value = CoursesUiState(
                isLoading = true
            )

            try {

                val courses = repository.getCourses()

                val uiModels = courses.map { course ->
                    course.toUiModel()
                }

                _uiState.value = CoursesUiState(
                    courses = uiModels
                )

            } catch (exception: Exception) {

                _uiState.value = CoursesUiState(
                    errorMessage = exception.message
                )

            }

        }

    }

}