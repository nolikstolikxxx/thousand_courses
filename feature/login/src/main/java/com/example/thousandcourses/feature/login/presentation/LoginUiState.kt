package com.example.thousandcourses.feature.login.presentation

/**
 * Data validation state of the login form.
 */
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false
)