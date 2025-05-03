package com.example.movieapp.api

import com.example.movieapp.model.MovieDetail
import com.example.movieapp.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "eae7af90f3a463843db1e118230aeefb",
        @Query("language") language: String = "en-US"
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "eae7af90f3a463843db1e118230aeefb",
        @Query("language") language: String = "en-US"
    ): Call<MovieDetail>
}
