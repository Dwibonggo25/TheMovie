package com.example.jetpack.api

import com.example.jetpack.model.MoviesResponse
import com.example.jetpack.model.NowPlayingMoviesResponse
import com.example.jetpack.model.TvMoviesResponse
import com.example.jetpack.model.UpcomingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun fetchPopularMovie (@Query("api_key") apiKey: String) : Observable<MoviesResponse>

    @GET("tv/top_rated")
    fun fetchTopRatesMovies (@Query("api_key") apiKey: String) : Observable<TvMoviesResponse>

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies (@Query("api_key") apiKey: String) : NowPlayingMoviesResponse

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies (@Query("api_key") apiKey: String) : UpcomingResponse
}