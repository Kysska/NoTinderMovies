package com.example.notindermovies.data.retrofit

import com.example.notindermovies.domain.entity.Movie
import retrofit2.http.GET
import retrofit2.http.Headers

interface MainAPI {

    @Headers(
        "X-RapidAPI-Key: 78d7a98f24msha5c47b458a59ac3p1d4af7jsn143f57133120",
        "X-RapidAPI-Host: imdb-top-100-movies.p.rapidapi.com"
    )
    @GET("/")
    suspend fun getMovies():List<Movie>

}