package com.example.thousandcourses.feature.login.di

import com.example.thousandcourses.feature.login.presentation.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {

    viewModel {
        LoginViewModel()
    }
}