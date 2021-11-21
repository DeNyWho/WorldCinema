package com.example.worldcinema.data

data class MoviesListItem(
    val age: String,
    val description: String,
    val images: List<Any>,
    val movieId: String,
    val name: String,
    val poster: String,
    val tags: List<Any>
)