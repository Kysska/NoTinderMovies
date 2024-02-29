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

@Module
class AppModule(val context:Context) {

    @Provides
    fun provideContext() :Context{
        return context
    }

    @Provides
    fun provideMainViewModelFactory(
        likeMovie: LikeMovie,
        getAllMovie: GetAllMovie
    ): MainViewModelFactory {
        return MainViewModelFactory(getAllMovie, likeMovie)
    }

    @Provides
    fun provideFavoriteMoviesViewModelFactory(
        getFavoriteList: GetFavoriteList
    ) : FavoriteMoviesViewModelFactory{
        return FavoriteMoviesViewModelFactory(getFavoriteList)
    }

    @Provides
    fun provideMainFragment(): MainFragment {
        return MainFragment()
    }

    @Provides
    fun provideLikeList(): LikeListFragment {
        return LikeListFragment()
    }
}