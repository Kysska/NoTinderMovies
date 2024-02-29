package com.example.notindermovies.presentation.screenfavoritemovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notindermovies.domain.usecase.GetFavoriteList

class FavoriteMoviesViewModelFactory(private val getFavoriteList: GetFavoriteList) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteMoviesViewModel(
            getFavoriteList
        ) as T
    }
}