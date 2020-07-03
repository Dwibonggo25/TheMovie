package com.example.jetpack.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack.Result
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.model.MoviesResponse
import com.example.jetpack.repository.PopularMoviesRepository
import com.example.jetpack.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularMoviesViewmodel @Inject constructor(private val repository: PopularMoviesRepository) : BaseViewmodel() {

    private val _dataMovies = MutableLiveData<Result<MoviesResponse>>()
    val dataMovies: LiveData<Result<MoviesResponse>>
        get() = _dataMovies

    fun fetchDataMovies() {
        mCompositeDisposable += repository.fetchPopularMovie()
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