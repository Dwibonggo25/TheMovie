package com.example.jetpack.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack.Result
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.model.NowPlayingMoviesResponse
import com.example.jetpack.model.UpcomingResponse
import com.example.jetpack.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: HomeRepository): BaseViewmodel() {

    private val _dataMovies = MutableLiveData<Result<NowPlayingMoviesResponse>>()
    val dataMovies: LiveData<Result<NowPlayingMoviesResponse>>
        get() = _dataMovies

    private val _upcomingMovies = MutableLiveData<Result<UpcomingResponse>>()
    val upcomingMovies: LiveData<Result<UpcomingResponse>>
        get() = _upcomingMovies

    fun fetchNowPlayingMovies () {

        setResult(Result.Loading())
        viewModelScope.launch (Dispatchers.IO){
            val data = repository.fetchNowPlaying()
            try {
                if (!data.results.isNullOrEmpty()){
                    setResult(Result.HasData(data))
                }else {
                    setResult(Result.NoData())
                }
            }catch (e : Throwable){

            }
        }
    }

    fun fetchUpcomingMovies () {
        setUpcomingResult(Result.Loading())
        viewModelScope.launch (Dispatchers.IO){
            val data = repository.fetchUpcomingMovies()
            try {
                if (!data.results.isNullOrEmpty()) {
                    setUpcomingResult(Result.HasData(data))
                }
                else {
                    setUpcomingResult(Result.NoData())
                }
            }catch (e : Throwable) {

            }
        }
    }

    fun setUpcomingResult (data: Result <UpcomingResponse>) {
        _upcomingMovies.postValue(data)
    }

    fun setResult(data: Result<NowPlayingMoviesResponse>){
        _dataMovies.postValue(data)
    }
}