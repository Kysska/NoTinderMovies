package com.example.notindermovies.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://imdb-top-100-movies.p.rapidapi.com"
    private const val API_KEY = "92f031b1aamsh2df15b25ee587eap148938jsnd36d36e14761"
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