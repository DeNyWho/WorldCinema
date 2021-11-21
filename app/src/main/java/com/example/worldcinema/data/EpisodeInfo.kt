package com.example.worldcinema.data

data class EpisodeInfo (
    val episodeId: String,
    val name: String,
    val description: String,
    val direction: String,
    val start: List<String>,
    val year: String,
    val images: List<String>,
    val runtime: String,
    val preview: String,
    val movieId: String
)