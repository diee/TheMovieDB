package com.example.themoviedb.app.presentation

import com.example.themoviedb.domain.Movie

data class MainState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val selectedMovie: Movie? = null,
    val scrollPosition : Int = 0
)
