package com.example.jetpack.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack.Result
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.model.MoviesResponse
import com.example.jetpack.model.TvMoviesResponse
import com.example.jetpack.repository.MoviesRepository
import com.example.jetpack.utils.plusAssign
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewmodel @Inject constructor(private val repository: MoviesRepository) : BaseViewmodel() {

    private val _dataMovies = MutableLiveData<Result<MoviesResponse>>()
    val dataMovies: LiveData<Result<MoviesResponse>>
        get() = _dataMovies

    fun fetchDataMovies() {
        mCompositeDisposable += repository.fetchMovies()
            .doOnSubscribe { setResult(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data ->
                    setResult(Result.HasData(data))},
                {error ->
                    Log.e("Errornya", error.toString())
                    setResult(Result.NoData())
                })
    }

    fun setResult(data: Result<MoviesResponse>){
        _dataMovies.postValue(data)
    }
}