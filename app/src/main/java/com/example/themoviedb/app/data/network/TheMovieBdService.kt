package com.example.themoviedb.app.data.network

import com.example.themoviedb.app.data.network.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieBdService {

    @GET("discover/movie?language=en&sort_by=popularity.desc")
    suspend fun getMovies(@Query("page") page: Int): MoviesResponse
}
