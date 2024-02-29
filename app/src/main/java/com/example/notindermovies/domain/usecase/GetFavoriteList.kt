package com.example.notindermovies.domain.usecase

import androidx.lifecycle.LiveData
import com.example.notindermovies.domain.MovieRepository
import com.example.notindermovies.domain.entity.Movie

class GetFavoriteList(private val repository: MovieRepository) {
    fun getFavoriteList() : LiveData<List<Movie>> {
        return repository.getFavoriteList()
    }
}