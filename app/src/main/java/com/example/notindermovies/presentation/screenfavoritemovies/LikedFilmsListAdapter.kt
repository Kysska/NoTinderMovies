package com.example.notindermovies.presentation.screenfavoritemovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notindermovies.R
import com.example.notindermovies.databinding.ItemLikedFilmBinding
import com.example.notindermovies.domain.entity.Movie
import com.squareup.picasso.Picasso

class LikedFilmsListAdapter : RecyclerView.Adapter<LikedFilmsListAdapter.LikedFilmViewHolder>() {

    class LikedFilmViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemLikedFilmBinding.bind(view)
        fun bind(movie : Movie){
            Picasso.get().load(movie.image).into(binding.imageView)
            binding.text.text = movie.title
        }
    }

    var films = listOf<Movie>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    var onCinemaLongClickListener: OnCinemaListLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedFilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_liked_film, parent, false)
        return LikedFilmViewHolder(view)
    }

    override fun getItemCount(): Int {
        return films.size
    }

    override fun onBindViewHolder(holder: LikedFilmViewHolder, position: Int) {
        holder.bind(films[position])
        val cinema = films[position]
        holder.view.setOnClickListener {
            onCinemaLongClickListener?.onCinemaLongClickListener(cinema)
            true
        }
    }

    interface OnCinemaListLongClickListener{
        fun onCinemaLongClickListener(cinema: Movie)
    }
}