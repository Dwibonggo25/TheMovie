package com.example.jetpack.api

import com.example.jetpack.model.MoviesResponse
import com.example.jetpack.model.TvMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun fetchMoviewFilm (@Query("api_key") apiKey: String) : Observable<MoviesResponse>

    @GET("tv/top_rated")
    fun fetchTvShowsFilm (@Query("api_key") apiKey: String) : Observable<TvMoviesResponse>
}