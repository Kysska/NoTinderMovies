package com.example.notindermovies.di

import com.example.notindermovies.domain.MovieRepository
import com.example.notindermovies.domain.usecase.GetAllMovie
import com.example.notindermovies.domain.usecase.GetFavoriteList
import com.example.notindermovies.domain.usecase.LikeMovie
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetFavoriteList(repository: MovieRepository) : GetFavoriteList{
        return GetFavoriteList(repository)
    }

    @Provides
    fun provideLikeMovie(repository: MovieRepository) : LikeMovie{
        return LikeMovie(repository)
    }

    @Provides
    fun getAllMovie(repository: MovieRepository) : GetAllMovie{
        return GetAllMovie(repository)
    }
}