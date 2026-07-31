package com.example.thousandcourses.feature.courses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thousandcourses.feature.courses.domain.repository.CoursesRepository
import com.example.thousandcourses.feature.courses.presentation.mapper.toUiModel
import com.example.thousandcourses.feature.courses.presentation.model.CourseUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val repository: CoursesRepository
) : ViewModel() {

    private fun String.normalizeSearch(): String =
        lowercase()
            .trim()

    private val _uiState = MutableStateFlow(CoursesUiState())

    private var allCourses: List<CourseUiModel> = emptyList()

    private var currentSortType =
        CourseSortType.PUBLISH_DATE

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

                repository.getCourses()

                repository.observeCourses()
                    .collect { courses ->

                        val uiCourses =
                            courses.map {
                                it.toUiModel()
                            }

                        allCourses = uiCourses

                        updateVisibleCourses()
                    }

            } catch (exception: Exception) {

                _uiState.value =
                    CoursesUiState(
                        errorMessage = exception.message
                    )
            }
        }
    }
    fun onSearchQueryChanged(
        query: String
    ) {

        _uiState.value =
            _uiState.value.copy(
                searchQuery = query
            )

        updateVisibleCourses()
    }

    fun sortCourses(
        sortType: CourseSortType
    ) {

        currentSortType = sortType

        updateVisibleCourses()
    }

    private fun updateVisibleCourses() {

        var visibleCourses = allCourses

        val query =
            _uiState.value.searchQuery

        if (query.isNotBlank()) {

            val normalizedQuery =
                query.normalizeSearch()

            visibleCourses =
                visibleCourses.filter { course ->

                    course.title
                        .normalizeSearch()
                        .contains(normalizedQuery) ||

                            course.description
                                .normalizeSearch()
                                .contains(normalizedQuery)

                }
        }

        visibleCourses =
            when (currentSortType) {

                CourseSortType.TITLE ->
                    visibleCourses.sortedBy {
                        it.title
                    }

                CourseSortType.RATING ->
                    visibleCourses.sortedByDescending {
                        it.rating.toDoubleOrNull() ?: 0.0
                    }

                CourseSortType.PUBLISH_DATE ->
                    visibleCourses.sortedByDescending {
                        it.publishDate
                    }
            }

        _uiState.value =
            _uiState.value.copy(
                courses = visibleCourses,
                isLoading = false
            )
    }

    fun onFavoriteClicked(
        courseId: Int
    ) {

        viewModelScope.launch {

            repository.toggleFavorite(
                courseId = courseId
            )

        }
    }
}

enum class CourseSortType {
    TITLE ,
    RATING ,
    PUBLISH_DATE
}