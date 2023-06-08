package com.example.themoviedb.domain

import com.example.themoviedb.app.presentation.mockedMovie
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {

    @Mock
    lateinit var mainRepository: MainRepository

    lateinit var SUT: GetMoviesUseCase

    @Before
    fun setUp() {
        SUT = GetMoviesUseCase(mainRepository)
    }

    @Test
    fun `invoke calls getMovies on mainRepository and returns movies`() = runTest {
        // Given
        val page = 1
        val movies = listOf(mockedMovie)
        whenever(mainRepository.getMovies(page)).thenReturn(flowOf(movies))

        // When
        val result = SUT.invoke(page).lastOrNull()

        // Then
        assertEquals(movies, result)
        verify(mainRepository).getMovies(page)
    }
}