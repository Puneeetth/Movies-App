package com.example.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.api.RetrofitClient
import com.example.movies.model.Movie
import com.example.movies.ui.theme.MovieCard

@Composable
fun HomeScreen(navController: NavController) {
    var movies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    var selectedIndustry by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }
    var sortType by remember { mutableStateOf("NONE") }

    LaunchedEffect(Unit) {
        try {
            movies = RetrofitClient.api.getAllMovies()
            errorMessage = null
        } catch (e: Exception) {
            errorMessage = "Failed to load movies: ${e.localizedMessage}"
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("Search Movie or Actor") }
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("All", "Telugu", "Tamil").forEach { industry ->
                FilterChip(
                    selected = selectedIndustry == industry,
                    onClick = { selectedIndustry = industry },
                    label = { Text(industry) }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AssistChip(onClick = { sortType = "RATING" }, label = { Text("Rating") })
            AssistChip(onClick = { sortType = "COLLECTION" }, label = { Text("Collection") })
            AssistChip(onClick = { sortType = "YEAR" }, label = { Text("Year") })
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.padding(16.dp))
            }
        } else {
            val filteredMovies = movies
                .filter {
                    (selectedIndustry == "All" || it.industry.equals(selectedIndustry, ignoreCase = true)) &&
                            (it.name.contains(searchQuery, true) ||
                                    it.actor.contains(searchQuery, true))
                }
                .let {
                    when (sortType) {
                        "RATING" -> it.sortedByDescending { m -> m.rating }
                        "COLLECTION" -> it.sortedByDescending { m -> m.collection }
                        "YEAR" -> it.sortedByDescending { m -> m.year }
                        else -> it
                    }
                }

            if (filteredMovies.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No movies found")
                }
            } else {
                LazyColumn {
                    items(filteredMovies) { movie ->
                        MovieCard(movie) {
                            navController.navigate("actor/${movie.actor}")
                        }
                    }
                }
            }
        }
    }
}
