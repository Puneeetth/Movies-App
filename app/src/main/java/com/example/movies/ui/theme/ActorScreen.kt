package com.example.movies.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.api.RetrofitClient
import com.example.movies.model.Movie

@Composable
fun ActorScreen(actorName : String){
    var actorMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(actorName) {
        try {
            actorMovies = RetrofitClient.api.getMovieByActor(actorName)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val bestMovie = actorMovies.maxByOrNull { it.rating }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = actorName, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))

            val totalMovies = actorMovies.size
            val highestGross = actorMovies.maxByOrNull { it.collection }?.collection ?: 0
            val avgRating = if (actorMovies.isNotEmpty()) actorMovies.map { it.rating }.average() else 0.0

            Text("Total Movies: $totalMovies")
            Text("Highest Gross: ₹${highestGross} Cr")
            Text("Avg Rating: ${"%.1f".format(avgRating)}")
            Text("Career Best: ${bestMovie?.name ?: "N/A"}")

            Spacer(modifier = Modifier.height(16.dp))

            Text("Movies", fontSize = 20.sp)

            LazyColumn {
                items(actorMovies) { movie ->
                    MovieCard(movie) {}
                }
            }
        }
    }
}