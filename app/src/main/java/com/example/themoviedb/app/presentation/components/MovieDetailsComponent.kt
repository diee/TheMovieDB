package com.example.themoviedb.app.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.R
import com.example.themoviedb.domain.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsComponent(movie: Movie, onClose: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.6f),
                contentScale = ContentScale.FillHeight,
                model = BuildConfig.BASE_URL_IMAGE + movie.posterPath,
                contentDescription = "Poster Image",
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            ) {
                TopAppBar(
                    modifier = Modifier
                        .background(Color.Transparent),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent.copy(alpha = 0.5f)
                    ),
                    title = { Text(text = movie.title, maxLines = 2, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
                DetailsContent(movie)
            }
        }

    }
    BackHandler { onClose() }
}

@Composable
fun DetailsContent(movie: Movie) {
    Column(
        modifier = Modifier
            .background(Color.Transparent.copy(alpha = 0.3f))
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ItemTextDetailsRow(stringResource(id = R.string.release_date_label), movie.releaseDate)
        ItemTextDetailsRow(
            stringResource(id = R.string.popularity_label),
            movie.popularity.toString()
        )
        ItemTextDetailsRow(
            stringResource(id = R.string.vote_average_label),
            movie.voteAverage.toString()
        )
        ItemTextDetailsRow(
            stringResource(id = R.string.vote_count_label),
            movie.voteCount.toString()
        )
        ItemTextDetailsRow(
            stringResource(id = R.string.original_language_label),
            movie.originalLanguage
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.overview_label),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}

@Composable
fun ItemTextDetailsRow(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}
