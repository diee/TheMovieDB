package com.example.themoviedb.data

import com.example.themoviedb.app.data.database.MovieDao
import com.example.themoviedb.app.data.mappers.toDomain
import com.example.themoviedb.app.data.network.TheMovieBdService
import com.example.themoviedb.app.presentation.mockedMovieDb
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainRepositoryImplTest {

    @Mock
    lateinit var service: TheMovieBdService

    @Mock
    lateinit var movieDao: MovieDao

    lateinit var SUT: MainRepositoryImpl

    @Before
    fun setUp() {
        SUT = MainRepositoryImpl(service, movieDao)
    }

    @Test
    fun `getMovies calls movieDao`() = runTest {
        // Given
        val page = 1
        val moviesDb = listOf(mockedMovieDb)
        whenever(movieDao.getMovies(page)).thenReturn(moviesDb)

        // When
        val result = SUT.getMovies(page).lastOrNull()

        // Then
        assertEquals(moviesDb.map { it.toDomain() }, result)
    }
}