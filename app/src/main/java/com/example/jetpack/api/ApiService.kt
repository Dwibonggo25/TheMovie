package com.example.jetpack.api

import com.example.jetpack.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/popular")
    fun fetchMoviewFilm (@Query("api_key") apiKey: String) : Observable<MoviesResponse>

    @GET("movie/{movie_id}}")
    suspend fun fetchDetailMovies ( @Path("movie_id") movieId: String, @Query("api_key") apiKey: String) : DetailMoviesResponse
}