package com.example.notindermovies.domain.entity

data class Movie(
    val rank :Int,
    val title: String,
    val description: String,
    val image: String,
    val big_image: String,
    val genre: List<String>,
    val thumbnail:String,
    val rating:String,
    val id: String,
    val year:Int,
    val imdbid:String,
    val imdb_link:String,
){
    var liked : Boolean = false
}
