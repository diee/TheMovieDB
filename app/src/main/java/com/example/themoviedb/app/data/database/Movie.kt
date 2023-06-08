package com.example.themoviedb.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val page: Int,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double,
    val originalLanguage: String
)