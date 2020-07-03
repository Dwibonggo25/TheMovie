package com.example.jetpack.model

data class NowPlayingMoviesResponse(
    val dates: Dates,
    val results: List<NowPlayingData>
)

data class NowPlayingData(
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val popularity: Double,
    val poster_path: String,
    val title: String,
    val vote_average: Double
)