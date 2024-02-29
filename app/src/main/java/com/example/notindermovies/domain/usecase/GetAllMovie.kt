package com.example.notindermovies.domain.usecase


import com.example.notindermovies.domain.MovieRepository
import com.example.notindermovies.domain.entity.Movie

class GetAllMovie(private val repository: MovieRepository) {
    suspend fun getAllCinema():List<Movie>{
        return repository.getAllMovie()
    }
}