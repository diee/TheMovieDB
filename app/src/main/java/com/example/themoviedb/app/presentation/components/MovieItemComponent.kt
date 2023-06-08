package com.example.themoviedb.app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.domain.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItemComponent(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(2.dp).semantics { contentDescription = movie.title },
        onClick = onClick
    ) {

        var isImageLoading by remember { mutableStateOf(false) }

        val painter = rememberAsyncImagePainter(
            model = BuildConfig.BASE_URL_IMAGE + movie.posterPath,
        )

        isImageLoading = when (painter.state) {
            is AsyncImagePainter.State.Loading -> true
            else -> false
        }

        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                painter = painter,
                contentDescription = "Poster Image",
                contentScale = ContentScale.Crop,
            )

            if (isImageLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 3.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth().height(50.dp).background(
                Color.Black.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(2.dp),
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}