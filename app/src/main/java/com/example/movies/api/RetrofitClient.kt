package com.example.movies.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // 192.168.1.2 is your computer's IP on the Wi-Fi network.
    // 192.168.137.1 is typically used if you are sharing your computer's internet via a Hotspot.
    
    // Use this if your phone is on the same Wi-Fi:
    private const val BASE_URL = "http://192.168.1.2:8080/"

    val api : MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}