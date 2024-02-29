package com.example.notindermovies.presentation.mainscreen


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
    val allMovie = MutableLiveData<List<Movie>>()

    init {
        fetchAllMovie()
    }

    fun fetchAllMovie() {
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val movies = getAllMovie.getAllCinema()
                allMovie.postValue(movies)
                randomAllMovie()
                isLoading.postValue(false)
            } catch (e: Exception) {
                // Обработка ошибки загрузки данных
                isLoading.postValue(false)
            }
        }
    }

    private fun randomAllMovie() {
        val movies = allMovie.value ?: return
        val shuffledMovies = movies.shuffled()
        allMovie.postValue(shuffledMovies)
    }

    fun getRandomMovie(index: Int) : Movie? {
        return allMovie.value?.getOrNull(index)
    }

    fun getCountMovie() : Int{
       return allMovie.value?.size ?: 0
    }

    fun likeMovie(index: Int): Boolean {
        return likeMovie.likeMovie(index)
    }


}