package com.example.themoviedb.app.data.mappers

import com.example.themoviedb.app.data.database.Movie as MovieDB
import com.example.themoviedb.app.data.network.models.Movie as MovieNetwork
import com.example.themoviedb.domain.Movie as MovieDomain


fun MovieNetwork.toDB(page: Int): MovieDB {
    return MovieDB(
        id = id,
        title = title,
        posterPath = poster_path.orEmpty(),
        backdropPath = backdrop_path.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = release_date.orEmpty(),
        voteAverage = vote_average ?: 0.0,
        voteCount = vote_count ?: 0,
        popularity = popularity ?: 0.0,
        originalLanguage = original_language.orEmpty(),
        page = page
    )
}

fun MovieDB.toDomain(): MovieDomain {
    return MovieDomain(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        originalLanguage = originalLanguage
    )
}