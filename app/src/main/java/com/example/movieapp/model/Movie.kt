package com.example.movieapp.model

import java.io.Serializable

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val vote_average: Double = 0.0
) : Serializable
