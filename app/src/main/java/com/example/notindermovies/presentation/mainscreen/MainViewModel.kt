package com.example.notindermovies.presentation.mainscreen


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notindermovies.domain.entity.Movie
import com.example.notindermovies.domain.usecase.LikeMovie
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.notindermovies.domain.usecase.GetAllMovie


class MainViewModel(
    private val getAllMovie: GetAllMovie,
    private val likeMovie: LikeMovie,
) :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    private val allMovie = MutableLiveData<List<Movie>>()

    private val _shuffledMoviesLD = MutableLiveData<List<Movie>>()
    val shuffledMovies : LiveData<List<Movie>>
        get() = _shuffledMoviesLD

    init {
        fetchAllMovie()
    }

    private fun fetchAllMovie() {
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                Log.d("yes&", "yes")
                val movies = getAllMovie.getAllCinema()
                allMovie.postValue(movies)
                isLoading.postValue(false)
            } catch (e: Exception) {
                isLoading.postValue(false)
            }
        }
    }

    fun randomAllMovieAndGetFirstMovie() : Movie? {
        val movies = allMovie.value ?: return null
        val shuffledMovies = movies.shuffled()
        _shuffledMoviesLD.value = shuffledMovies
        return shuffledMovies[0]
    }

    fun getRandomMovie(index: Int) : Movie? {
        _shuffledMoviesLD.value?.joinToString()?.let { Log.d("shuff", it) }
        val movie = _shuffledMoviesLD.value?.getOrNull(index)
        movie?.let { Log.d("why", it.id) }
        return movie
    }

    fun getCountMovie() : Int{
       return allMovie.value?.size ?: 0
    }

    fun likeMovie(index: Int): Boolean? {
        return _shuffledMoviesLD.value?.get(index)?.let {
            if(!it.liked){
               return likeMovie.likeMovie(it.rank - 1)
            }
            else{
                return false
            }
        }
    }


}