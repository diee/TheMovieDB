package com.example.themoviedb.domain

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getMovies(page: Int): Flow<List<Movie>>
}