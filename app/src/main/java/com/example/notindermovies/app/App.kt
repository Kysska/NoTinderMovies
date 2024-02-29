package com.example.notindermovies.app

import android.app.Application
import com.example.notindermovies.di.AppComponent
import com.example.notindermovies.di.AppModule
import com.example.notindermovies.di.DaggerAppComponent

class App : Application(){
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}