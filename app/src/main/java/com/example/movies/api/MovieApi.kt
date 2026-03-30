package com.example.movies.api

import com.example.movies.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    // If your Spring Controller has @RequestMapping("/movies"), keep "movies"
    // If it DOES NOT have @RequestMapping at the class level, change these to "", "actor/{actor}", etc.
    
    @GET("movies") 
    suspend fun getAllMovies(): List<Movie>

    @GET("movies/actor/{actor}")
    suspend fun getMovieByActor(@Path("actor") actor: String): List<Movie>

    @GET("movies/industry/{industry}")
    suspend fun getMovieByIndustry(@Path("industry") industry: String): List<Movie>
}