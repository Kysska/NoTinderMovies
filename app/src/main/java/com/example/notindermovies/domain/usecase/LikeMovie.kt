package com.example.notindermovies.domain.usecase

import com.example.notindermovies.domain.MovieRepository

class LikeMovie(private val repository: MovieRepository) {
    fun likeMovie(index:Int) : Boolean{
        return repository.likeMovie(index)
    }
}