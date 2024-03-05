package com.example.notindermovies.di

import com.example.notindermovies.presentation.MainActivity
import com.example.notindermovies.presentation.mainscreen.MainFragment
import com.example.notindermovies.presentation.screenfavoritemovies.LikeListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: LikeListFragment)
}