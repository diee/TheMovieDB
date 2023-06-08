package com.example.themoviedb.app.presentation

import com.example.themoviedb.domain.Movie
import com.example.themoviedb.app.data.database.Movie as MovieDb

val mockedMovie = Movie(
    id = 1,
    title = "Movie 1",
    posterPath = "",
    backdropPath = "",
    releaseDate = "01/01/2021",
    overview = "Overview 1",
    voteAverage = 1.0,
    voteCount = 1,
    popularity = 1.0,
    originalLanguage = "en"
)

val mockedMovieDb = MovieDb(
    id = 1,
    title = "Movie 1",
    posterPath = "",
    backdropPath = "",
    releaseDate = "01/01/2021",
    overview = "Overview 1",
    voteAverage = 1.0,
    voteCount = 1,
    popularity = 1.0,
    originalLanguage = "en",
    page = 1
)