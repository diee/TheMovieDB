package com.example.themoviedb.app.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.themoviedb.domain.GetMoviesUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var SUT: MainViewModel

    @Mock
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        SUT = MainViewModel(getMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getMovies updates state correctly on success`() = runTest {

        // Given
        val movies = listOf(mockedMovie)
        whenever(getMoviesUseCase.invoke(any())).thenReturn(flowOf(movies))

        // When
        SUT.getMovies()

        // Then
        assertEquals(false, SUT.state.value.isLoading)
        assertNull(SUT.state.value.error)
        assertEquals(movies, SUT.state.value.movies)
    }

    @Test
    fun `getMovies updates state correctly on error`() = runTest {
        // Given
        val errorMessage = "An error occurred"
        whenever(getMoviesUseCase.invoke(any())).thenThrow(RuntimeException(errorMessage))

        // When
        SUT.getMovies()

        // Then
        assertEquals(false, SUT.state.value.isLoading)
        assertEquals(errorMessage, SUT.state.value.error)
        assertTrue(SUT.state.value.movies.isEmpty())
    }

    @Test
    fun `onMovieSelected updates selectedMovie in state`() {
        // Given
        val movie = mockedMovie

        // When
        SUT.onMovieSelected(movie)

        // Then
        assertEquals(movie, SUT.state.value.selectedMovie)
    }

    @Test
    fun `clearSelectedMovie sets selectedMovie to null in state`() {
        // Given
        SUT.onMovieSelected(mockedMovie)

        // When
        SUT.clearSelectedMovie()

        // Then
        assertNull(SUT.state.value.selectedMovie)
    }

    @Test
    fun `setScrollLastPosition updates scrollPosition in state`() {
        // Given
        val position = 42

        // When
        SUT.setScrollLastPosition(position)

        // Then
        assertEquals(position, SUT.state.value.scrollPosition)
    }
}