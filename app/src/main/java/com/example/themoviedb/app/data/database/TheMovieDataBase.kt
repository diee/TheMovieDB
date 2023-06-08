package com.example.themoviedb.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class TheMovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}