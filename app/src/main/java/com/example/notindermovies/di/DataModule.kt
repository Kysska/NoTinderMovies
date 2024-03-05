package com.example.notindermovies.di

import android.content.Context
import com.example.notindermovies.data.MovieRepositoryImpl
import com.example.notindermovies.domain.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideMovieRepository(context: Context) :MovieRepository{
        return MovieRepositoryImpl(context)
    }
}