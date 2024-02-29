package com.example.notindermovies.presentation.screenfavoritemovies

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notindermovies.R
import com.example.notindermovies.app.App
import com.example.notindermovies.databinding.FragmentLikeListBinding
import com.example.notindermovies.presentation.mainscreen.MainFragment


class LikeListFragment : Fragment() {


    private lateinit var binding: FragmentLikeListBinding
    private lateinit var viewModel: FavoriteMoviesViewModel
    @javax.inject.Inject
    lateinit var vmFactory: FavoriteMoviesViewModelFactory
    private val adapter = LikedFilmsListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(FavoriteMoviesViewModel::class.java)
        viewModel.likeMovie.observe(requireActivity()){
            adapter.films = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rv.layoutManager = GridLayoutManager(requireContext(), 3)
            rv.adapter = adapter
        }
    }

    companion object {
        const val TAG = "LikeListFragment"

        fun newInstance() : LikeListFragment {
            return LikeListFragment()
        }
    }
}