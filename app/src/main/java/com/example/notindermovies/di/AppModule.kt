package com.example.notindermovies.di

import android.content.Context
import com.example.notindermovies.domain.usecase.GetAllMovie
import com.example.notindermovies.domain.usecase.GetFavoriteList
import com.example.notindermovies.domain.usecase.LikeMovie
import com.example.notindermovies.presentation.mainscreen.MainFragment
import com.example.notindermovies.presentation.mainscreen.MainViewModelFactory
import com.example.notindermovies.presentation.screenfavoritemovies.FavoriteMoviesViewModel
import com.example.notindermovies.presentation.screenfavoritemovies.FavoriteMoviesViewModelFactory
import com.example.notindermovies.presentation.screenfavoritemovies.LikeListFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context:Context) {

    @Provides
    @Singleton
    fun provideContext() :Context{
        return context
    }

    @Provides
    @Singleton
    fun provideMainViewModelFactory(
        likeMovie: LikeMovie,
        getAllMovie: GetAllMovie
    ): MainViewModelFactory {
        return MainViewModelFactory(getAllMovie, likeMovie)
    }

    @Provides
    @Singleton
    fun provideFavoriteMoviesViewModelFactory(
        getFavoriteList: GetFavoriteList
    ) : FavoriteMoviesViewModelFactory{
        return FavoriteMoviesViewModelFactory(getFavoriteList)
    }

    @Provides
    @Singleton
    fun provideMainFragment(): MainFragment {
        return MainFragment()
    }

    @Provides
    @Singleton
    fun provideLikeList(): LikeListFragment {
        return LikeListFragment()
    }
}