package com.example.jetpack.model

data class UpcomingResponse(
    val page: Int,
    val results: List<UpcomingData>,
    val total_pages: Int,
    val total_results: Int
)

data class UpcomingData(
    val backdrop_path: String,
    val id: Int,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)