package com.example.movies.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.data.DummyData

@Composable
fun ActorScreen(actorName : String){
    val actorMovies = DummyData.movies.filter { it.actor == actorName }
    val bestMovie = actorMovies.maxByOrNull { it.rating }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = actorName, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))

        val totalMovies = actorMovies.size
        val highestGross = actorMovies.maxByOrNull { it.collection }?.collection ?: 0
        val avgRating = actorMovies.map {it.rating} .average()

        Text("Total Movies: $totalMovies")
        Text("Highest Gross: ₹${highestGross} Cr")
        Text("Avg Rating: ${"%.1f".format(avgRating)}")
        Text("Career Best: ${bestMovie?.name}")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Movies", fontSize = 20.sp)

        LazyColumn {
            items(actorMovies) { movie ->
                MovieCard(movie) {}
            }
        }
    }
}