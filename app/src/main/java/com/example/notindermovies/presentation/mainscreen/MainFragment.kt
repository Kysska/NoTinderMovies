package com.example.notindermovies.presentation.mainscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.notindermovies.R
import com.example.notindermovies.app.App
import com.example.notindermovies.databinding.FragmentMainBinding
import com.example.notindermovies.domain.entity.Movie
import kotlin.math.abs
import kotlin.properties.Delegates


class MainFragment : Fragment(), GestureDetector.OnGestureListener {

    @javax.inject.Inject
    lateinit var vmFactory : MainViewModelFactory
    private lateinit var binding: FragmentMainBinding
    lateinit var gestureDefector: GestureDetector
    private lateinit var viewModel: MainViewModel
    private var currentMovieIndex = 0
    private var isLoad = true
    private val fragmentStack = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), vmFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gestureDefector = GestureDetector(requireContext(), this)
        view.setOnTouchListener { view, motionEvent ->
            gestureDefector.onTouchEvent(motionEvent)
            true
        }
        fragmentStack.forEach { fragment ->
            childFragmentManager.beginTransaction()
                .replace(binding.mainFragment.id, fragment)
                .addToBackStack(TAG + currentMovieIndex.toString())
                .commit()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                // Показать индикатор загрузки
            } else if(isLoad) {

                val movie = viewModel.randomAllMovieAndGetFirstMovie()
                if (movie != null) {
                    nextFilm(movie)
                } else {
                    Log.e("Fragment", "getRandomMovie returned null")
                }
                isLoad = false
            }
        }
        binding.floatingButton.setOnClickListener {
            viewModel.likeMovie(currentMovieIndex-1)
        }
    }

    private fun prevMovie(){
        childFragmentManager.popBackStack()
        currentMovieIndex-=1
        fragmentStack.removeAt(fragmentStack.size - 1)
    }


    private fun nextFilm(movie: Movie){
        val movieFragment = MovieFragment.newInstance(image = movie.image,
        title = movie.title,
            year = movie.year,
            description = movie.description,
            rating = movie.rating,
            list_category = movie.genre
            )
        childFragmentManager.beginTransaction().replace(binding.mainFragment.id, movieFragment).addToBackStack(
            TAG + currentMovieIndex.toString()).commit()
        currentMovieIndex++
        fragmentStack.add(movieFragment)
    }


    override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        try {
            if (p0 != null) {
                if (abs(p0.x - p1.x) > SWIPE_MAX_OFF_PATH) {
                    return false
                }
            }
            if (p0 != null) {
                if (p0.y - p1.y > SWIPE_MIN_DISTANCE && abs(p3) > SWIPE_THRESHOLD_VELOCITY) {
                    if (currentMovieIndex < viewModel.getCountMovie() && !isLoad) {
                        viewModel.getRandomMovie(currentMovieIndex)?.let { nextFilm(it) }
                    }
                    Log.d("swipe", "Down to Up")
                } else if (p1.y - p0.y > SWIPE_MIN_DISTANCE && abs(p3) > SWIPE_THRESHOLD_VELOCITY) {
                    if (currentMovieIndex > 1 && !isLoad) {
                        prevMovie()
                    }
                    Log.d("swipe", "Up to Down")
                }
            }
        } catch (_: Exception) {
        }
        return true
    }


    override fun onDown(p0: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(p0: MotionEvent) {

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return false
    }


    override fun onScroll(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent) {
    }


    companion object {
        private const val SWIPE_MIN_DISTANCE = 120
        private const val SWIPE_MAX_OFF_PATH = 250
        private const val SWIPE_THRESHOLD_VELOCITY = 200

        const val TAG = "MainFragment"

        fun newInstance() : MainFragment{
            return MainFragment()
        }

    }

}