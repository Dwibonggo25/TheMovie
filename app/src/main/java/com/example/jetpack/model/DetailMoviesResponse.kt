package com.example.jetpack.model

data class DetailMoviesResponse(
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val genres: List<Genre>,
    val id: Int,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val release_date: String,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)