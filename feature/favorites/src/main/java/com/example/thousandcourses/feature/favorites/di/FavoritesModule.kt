package com.example.thousandcourses.feature.favorites.di

import com.example.thousandcourses.feature.favorites.presentation.FavoritesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {

    viewModel {

        FavoritesViewModel(
            repository = get()
        )

    }

}