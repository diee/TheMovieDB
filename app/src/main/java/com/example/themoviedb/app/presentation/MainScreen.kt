package com.example.themoviedb.app.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.themoviedb.app.presentation.components.ErrorComponent
import com.example.themoviedb.app.presentation.components.MovieDetailsComponent
import com.example.themoviedb.app.presentation.components.MovieItemComponent
import com.example.themoviedb.app.presentation.components.MovieListComponent
import com.example.themoviedb.app.presentation.components.MoviesLoaderComponent

@Composable
fun MainScreen(
    widthSizeClass: WindowWidthSizeClass,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

    if (isExpandedScreen) {
        Row(modifier = Modifier.fillMaxWidth()) {
            MovieListComponent(
                modifier = if (state.selectedMovie == null) Modifier else Modifier.weight(0.4f),
                state = state,
                onSaveScrollPosition = viewModel::setScrollLastPosition,
                onGetMovies = viewModel::getMovies,
                onMovieSelected = viewModel::onMovieSelected
            )
            AnimatedVisibility(
                modifier = Modifier.weight(0.6f),
                visible = state.selectedMovie != null
            ) {
                Box {
                    state.selectedMovie?.let { MovieDetailsComponent(it) { viewModel.clearSelectedMovie() } }
                }
            }
        }
    } else {
        if (state.selectedMovie != null) {
            Box(modifier = Modifier.fillMaxWidth()) {
                state.selectedMovie?.let { MovieDetailsComponent(it) { viewModel.clearSelectedMovie() } }
            }
        } else {
            MovieListComponent(
                state = state,
                onSaveScrollPosition = viewModel::setScrollLastPosition,
                onGetMovies = viewModel::getMovies,
                onMovieSelected = viewModel::onMovieSelected
            )
        }
    }
}

