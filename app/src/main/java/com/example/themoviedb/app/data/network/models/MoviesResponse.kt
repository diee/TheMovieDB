package com.example.themoviedb.app.data.network.models

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val overview: String? = null,
    val release_date: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val popularity: Double? = null,
    val original_language: String? = null
)
