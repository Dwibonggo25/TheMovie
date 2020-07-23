package com.example.jetpack.repository

import com.example.jetpack.API_KEY
import com.example.jetpack.api.ApiService
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.model.MoviesResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(private val api: ApiService) {

    fun fetchPopularMovie() : Observable <MoviesResponse> = api.fetchPopularMovie(API_KEY)

}