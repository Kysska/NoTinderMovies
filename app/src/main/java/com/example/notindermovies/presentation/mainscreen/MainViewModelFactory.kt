package com.example.notindermovies.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notindermovies.domain.usecase.GetAllMovie
import com.example.notindermovies.domain.usecase.LikeMovie

class MainViewModelFactory(
    private val getAllMovie: GetAllMovie,
    private val likeMovie: LikeMovie,
) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getAllMovie,
            likeMovie,
            ) as T
    }
}