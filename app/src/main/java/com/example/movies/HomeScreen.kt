package com.example.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.movies.data.DummyData
import com.example.movies.ui.theme.MovieCard

@Composable
fun HomeScreen(navController: NavController) {
    var movies by remember { mutableStateOf(DummyData.movies) }
    var selectedIndustry by remember { mutableStateOf("All")}
    var searchQuery by remember { mutableStateOf("") }
    var sortType by remember { mutableStateOf("NONE") }


    Column {
        TextField(
            value = searchQuery,
            onValueChange = {searchQuery = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = {Text("Search Movie or Actor")}
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("All","Telugu","Tamil").forEach { industry ->
                Button(onClick = {
                    selectedIndustry = industry
                }) { Text(industry)}
            }
        }
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
//            Button(onClick = {
//                movies = movies.sortedByDescending { it.rating }
//            }) {
//                Text("Sort by Rating")
//            }
//
//            Button(onClick = {
//                movies = movies.sortedByDescending { it.collection }
//            }) {
//                Text("Sort by Collection")
//            }
//
//            Button(onClick = {
//                movies = movies.sortedByDescending { it.year }
//            }) {
//                Text("Sort by Year")
//            }
//        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { sortType = "RATING" }) {
                Text("Sort by Rating")
            }

            Button(onClick = { sortType = "COLLECTION" }) {
                Text("Sort by Collection")
            }

            Button(onClick = { sortType = "YEAR" }) {
                Text("Sort by Year")
            }
        }

        val filteredMovies = DummyData.movies
            .filter {
                (selectedIndustry == "All" || it.industry == selectedIndustry) &&
                        (it.name.contains(searchQuery, true) ||
                                it.actor.contains(searchQuery, true))
            }
            .let {
                when (sortType) {
                    "RATING" -> it.sortedByDescending { it.rating }
                    "COLLECTION" -> it.sortedByDescending { it.collection }
                    "YEAR" -> it.sortedByDescending { it.year }
                    else -> it
                }
            }
        LazyColumn {
            items(filteredMovies) { movie ->
                MovieCard(movie){
                    navController.navigate("actor/${movie.actor}")
                }
            }

        }
    }
}
