package com.example.notindermovies.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notindermovies.data.retrofit.RetrofitClient
import com.example.notindermovies.data.retrofit.RetrofitClient.apiService
import com.example.notindermovies.domain.MovieRepository
import com.example.notindermovies.domain.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieRepositoryImpl(private val context : Context) : MovieRepository {
    private val allMovieLD = MutableLiveData<List<Movie>>()
    private val allMovie = mutableListOf<Movie>()


    private val likedMovieLD = MutableLiveData<List<Movie>>()
    private val likedMovie = mutableListOf<Movie>()

    val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    private val filmsDeferred by lazy {
        CoroutineScope(Dispatchers.IO).async {
            apiService.getMovies()
        }
    }
    init{
        getFirstFavoriteList()
    }

    private suspend fun getAwaitListMovie() : List<Movie>{
        return filmsDeferred.await()
    }


    private fun getFirstFavoriteList(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movies = getAwaitListMovie()
                movies.filterIndexed { index, movie -> getBoolean(movie.id) }.forEachIndexed { index, movie ->
                    movie.liked = true
                    likedMovie.add(index, movie)
                }
                likedMovieLD.postValue(likedMovie)
            } catch (e: Exception) {
                Log.e("MovieRepository", "Error fetching favorite movies: ${e.message}")
            }
        }
    }

    override fun getFavoriteList(): LiveData<List<Movie>> {
        Log.d("likedMovie", likedMovieLD.toString())
        return likedMovieLD
    }

    override fun likeMovie(index: Int): Boolean {
        return try {
            if (index < allMovie.size) {
                val result = saveBoolean(allMovie[index].id, true)
                if (result) {
                    allMovie[index].liked = true
                    likedMovie.add(allMovie[index])
                    likedMovieLD.postValue(likedMovie)
                }
                result
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error liking movie: ${e.message}")
            false
        }
    }


    override suspend fun getAllMovie(): List<Movie> {
        return try {
            allMovie.clear()
            allMovie.addAll(
                getAwaitListMovie()
            )
            allMovieLD.postValue(allMovie)
            Log.d("movieall", allMovie.size.toString())
            allMovie
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching all movies: ${e.message}")
            emptyList()
        }
    }

    private fun saveBoolean(key: String, value: Boolean): Boolean {
        Log.d("save", key)
        return try {
            val editor = sharedPreferences.edit()
            editor.putBoolean(key, value)
            editor.commit()
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error saving boolean: ${e.message}")
            false
        }
    }

    private fun getBoolean(key: String): Boolean {
        Log.d("get", key)
        return try {
            sharedPreferences.getBoolean(key, DEFAULT_VALUE)
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error getting boolean: ${e.message}")
            false
        }
    }

    companion object{
        private const val SHARED_PREFS_NAME = "movie_shared_prefs"
        private const val DEFAULT_VALUE = false
    }
}