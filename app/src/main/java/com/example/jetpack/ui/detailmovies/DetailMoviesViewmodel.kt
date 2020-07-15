package com.example.jetpack.ui.detailmovies

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack.Result
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.model.DetailMoviesResponse
import com.example.jetpack.model.NowPlayingMoviesResponse
import com.example.jetpack.repository.DetailMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DetailMoviesViewmodel @Inject constructor(private val repository: DetailMoviesRepository) : BaseViewmodel() {

    private val _dataMovies = MutableLiveData<Result<DetailMoviesResponse>>()
    val dataMovies: LiveData<Result<DetailMoviesResponse>>
        get() = _dataMovies

    private val movies = ObservableField<DetailMoviesResponse>()

    fun fetchMovieData (movieId: String) {
        viewModelScope.launch (Dispatchers.IO) {
            val data = repository.fetchDetailMovies(movieId)
            try {
                setResult(Result.HasData(data))
            }catch (e : Exception) {

            }
        }
    }

    fun setResult (data : Result<DetailMoviesResponse>){
        _dataMovies.postValue(data)
    }
}