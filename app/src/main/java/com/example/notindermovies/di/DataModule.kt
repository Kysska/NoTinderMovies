package com.example.notindermovies.di

import android.content.Context
import com.example.notindermovies.data.MovieRepositoryImpl
import com.example.notindermovies.domain.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideMovieRepository(context: Context) :MovieRepository{
        return MovieRepositoryImpl(context)
    }
}