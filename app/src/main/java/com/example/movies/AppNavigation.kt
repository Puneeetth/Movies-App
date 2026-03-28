package com.example.movies

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.ui.theme.ActorScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController)
        }
        composable("actor/{actorName}"){backStackEntry ->
            val actorName = backStackEntry.arguments?.getString("actorName") ?: ""
            ActorScreen(actorName)
        }
    }
}