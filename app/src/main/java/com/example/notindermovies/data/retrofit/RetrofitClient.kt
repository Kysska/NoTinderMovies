package com.example.notindermovies.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://imdb-top-100-movies.p.rapidapi.com"
    private const val API_KEY = "78d7a98f24msha5c47b458a59ac3p1d4af7jsn143f57133120"
    private const val API_HOST = "imdb-top-100-movies.p.rapidapi.com"

    private val interceptor = HttpLoggingInterceptor()
    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MainAPI = retrofit.create(MainAPI::class.java)

    fun getApiKey(): String = API_KEY

    fun getApiHost(): String = API_HOST
}