package com.example.worldcinema.data

data class MoviesListItem(
    val age: String,
    val description: String,
    val images: ArrayList<String>,
    val movieId: String,
    val name: String,
    val poster: String,
    val tags: ArrayList<String>
)