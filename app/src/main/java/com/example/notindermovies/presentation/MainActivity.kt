package com.example.notindermovies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notindermovies.R
import com.example.notindermovies.app.App
import com.example.notindermovies.databinding.ActivityMainBinding
import com.example.notindermovies.di.AppModule
import com.example.notindermovies.di.DaggerAppComponent
import com.example.notindermovies.presentation.mainscreen.MainFragment
import com.example.notindermovies.presentation.screenfavoritemovies.LikeListFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var mainFragment: MainFragment

    @Inject
    lateinit var favoriteFragment: LikeListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
        showMainFragment()
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.favorite->showFavoriteFragment()
                R.id.all_films->showMainFragment()
                else -> {}
            }
            true

        }
    }
    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFrame.id, mainFragment)
            .commit()
    }

    private fun showFavoriteFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFrame.id, favoriteFragment)
            .commit()
    }


}