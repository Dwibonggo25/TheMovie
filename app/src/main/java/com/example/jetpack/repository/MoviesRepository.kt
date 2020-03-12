package com.example.jetpack.repository

import com.example.jetpack.API_KEY
import com.example.jetpack.api.ApiService
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.model.MoviesResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: ApiService) {


    fun fetchMovies() : Observable <MoviesResponse> = api.fetchMoviewFilm(API_KEY)


}