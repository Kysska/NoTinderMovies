package com.example.notindermovies.presentation.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmslikes.presentation.customview.CustomViewCategory
import com.example.notindermovies.R
import com.example.notindermovies.databinding.FragmentMovieBinding
import com.squareup.picasso.Picasso
import java.util.ArrayList


class MovieFragment : Fragment() {

    lateinit var binding: FragmentMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(getImage()).into(binding.imageView2)
        binding.titleName.text = getTitle()
        binding.titleYear.text = getYear().toString()
        binding.titleDescription.text = getDescription()
        binding.rating.text = getRating()
        for(view in getList()!!){
            binding.listCategory.addView(view)
        }

    }

    private fun getTitle(): String? {
        return requireArguments().getString(TITLE)
    }

    private fun getYear(): Int? {
        return requireArguments().getInt(YEAR)
    }

    private fun getDescription(): String? {
        return requireArguments().getString(DESCRIPTION)
    }

    private fun getImage(): String? {
        return requireArguments().getString(IMAGE)
    }

    private fun getRating(): String?{
        return requireArguments().getString(RATING)
    }

    private fun getList():List<View>?{
        val listCategory : List<String>? = requireArguments().getStringArrayList(LIST_CATEGORY)
        val listView = mutableListOf<View>()
        if (listCategory != null) {
            for (category in listCategory){
                listView.add(CustomViewCategory(requireContext(), category))
            }
        }
        return listView
    }

    companion object {
        const val NAME = "MainFragment"

        const val IMAGE = "Image"
        const val TITLE = "Title"
        const val YEAR = "Year"
        const val DESCRIPTION = "Description"
        const val RATING = "Rating"
        const val LIST_CATEGORY = "List_Category"

        fun newInstance(image:String, title :String, year : Int, description : String, rating: String, list_category: List<String>) : MovieFragment{
            val arguments = Bundle().apply {
                putString(IMAGE, image)
                putString(TITLE, title)
                putInt(YEAR, year)
                putString(DESCRIPTION, description)
                putString(RATING, rating)
                putStringArrayList(LIST_CATEGORY, list_category as ArrayList<String>)
            }
            val fragment = MovieFragment()
            fragment.arguments = arguments
            return fragment
        }

    }
}