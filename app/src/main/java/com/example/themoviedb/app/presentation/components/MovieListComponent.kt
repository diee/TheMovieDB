package com.example.themoviedb.app.presentation.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.themoviedb.app.presentation.MainState
import com.example.themoviedb.domain.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun MovieListComponent(
    modifier: Modifier = Modifier,
    state: MainState,
    onSaveScrollPosition: (Int) -> Unit,
    onGetMovies: () -> Unit,
    onMovieSelected: (Movie) -> Unit
) {

    val lazyState = rememberLazyGridState(initialFirstVisibleItemIndex = state.scrollPosition)

    LaunchedEffect(key1 = lazyState) {
        snapshotFlow { lazyState.firstVisibleItemIndex }
            .debounce(300L)
            .collectLatest {
                onSaveScrollPosition(it)
            }
    }

    LazyVerticalGrid(
        modifier = modifier,
        state = lazyState,
        columns = GridCells.Adaptive(180.dp),
    ) {
        itemsIndexed(state.movies) { index, movie ->
            if (index >= state.movies.size - 1 && !state.isLoading && state.error == null) {
                onGetMovies()
            }
            MovieItemComponent(movie = movie) { onMovieSelected(movie) }
        }
        if (state.isLoading) {
            item(span = { GridItemSpan(Int.MAX_VALUE) }) { MoviesLoaderComponent() }
        }
        if (state.error != null) {
            item(span = { GridItemSpan(Int.MAX_VALUE) }) { ErrorComponent(state.error) { onGetMovies() } }
        }
    }
}