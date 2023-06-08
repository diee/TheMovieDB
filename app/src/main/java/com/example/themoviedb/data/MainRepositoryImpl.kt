package com.example.themoviedb.data

import com.example.themoviedb.app.data.database.MovieDao
import com.example.themoviedb.app.data.database.TheMovieDataBase
import com.example.themoviedb.app.data.mappers.toDB
import com.example.themoviedb.app.data.mappers.toDomain
import com.example.themoviedb.app.data.network.TheMovieBdService
import com.example.themoviedb.domain.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: TheMovieBdService,
    private val movieDao: MovieDao
) : MainRepository {

    override fun getMovies(page: Int) = flow {
        val movies = movieDao.getMovies(page)
        if (movies.isEmpty()) {
            val response = service.getMovies(page)
            val moviesDB = response.results.map { it.toDB(page) }
            movieDao.insertMovies(moviesDB)
            emit(moviesDB.map { it.toDomain() })
        } else {
            emit(movies.map { it.toDomain() })
        }

    }.flowOn(Dispatchers.IO)
}