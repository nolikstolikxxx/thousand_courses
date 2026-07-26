package com.example.thousandcourses.feature.login.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun onEmailChanged(email: String) {

        _uiState.value = _uiState.value.copy(
            email = email,
            isLoginEnabled = validate(
                email,
                _uiState.value.password
            )
        )
    }


    fun onPasswordChanged(password: String) {

        _uiState.value = _uiState.value.copy(
            password = password,
            isLoginEnabled = validate(
                _uiState.value.email,
                password
            )
        )
    }


    private fun validate(
        email: String,
        password: String
    ): Boolean {

        val emailRegex =
            Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")

        return email.matches(EMAIL_REGEX)
                && password.isNotBlank()
    }

    private companion object {

        val EMAIL_REGEX = Regex(
            "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"
        )
    }
}