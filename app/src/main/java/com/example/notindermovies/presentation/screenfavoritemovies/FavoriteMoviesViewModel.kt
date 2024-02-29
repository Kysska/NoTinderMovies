package com.example.notindermovies.presentation.screenfavoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notindermovies.domain.entity.Movie
import com.example.notindermovies.domain.usecase.GetFavoriteList

class FavoriteMoviesViewModel(
    private val getFavoriteList: GetFavoriteList
) : ViewModel() {
    val likeMovie : LiveData<List<Movie>> = getFavoriteList.getFavoriteList()
}