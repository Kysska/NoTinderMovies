package com.example.notindermovies.domain

import androidx.lifecycle.LiveData
import com.example.notindermovies.domain.entity.Movie

interface MovieRepository {
    fun getFavoriteList() : LiveData<List<Movie>>
    fun likeMovie(index : Int) : Boolean
    suspend fun getAllMovie() : List<Movie>
}