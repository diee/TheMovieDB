package com.example.themoviedb.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.domain.GetMoviesUseCase
import com.example.themoviedb.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()
    var page = 1

    init {
        getMovies()
    }

    fun getMovies() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                getMoviesUseCase.invoke(page)
                    .collect { movies ->
                        val currentMovies = _state.value.movies
                        _state.update { state ->
                            state.copy(
                                isLoading = false,
                                error = null,
                                movies = currentMovies + movies
                            )
                        }
                        page++
                    }
            } catch (ex: Exception) {
                _state.update { it.copy(isLoading = false, error = ex.message.orEmpty()) }
            }

        }
    }

    fun retry() {
        getMovies()
    }

    fun onMovieSelected(movie: Movie) {
        _state.value = _state.value.copy(selectedMovie = movie)
    }

    fun clearSelectedMovie() {
        _state.value = _state.value.copy(selectedMovie = null)
    }

    fun setScrollLastPosition(position: Int) {
        _state.value = _state.value.copy(scrollPosition = position)
    }
}
