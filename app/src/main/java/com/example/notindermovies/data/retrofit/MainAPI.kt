package com.example.notindermovies.data.retrofit

import com.example.notindermovies.domain.entity.Movie
import retrofit2.http.GET
import retrofit2.http.Headers

interface MainAPI {

    @Headers(
        "X-RapidAPI-Key: 92f031b1aamsh2df15b25ee587eap148938jsnd36d36e14761",
        "X-RapidAPI-Host: imdb-top-100-movies.p.rapidapi.com"
    )
    @GET("/")
    suspend fun getMovies():List<Movie>

}