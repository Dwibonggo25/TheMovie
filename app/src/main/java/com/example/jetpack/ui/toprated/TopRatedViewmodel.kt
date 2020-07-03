package com.example.jetpack.ui.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack.Result
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.model.TvMoviesResponse
import com.example.jetpack.repository.TopRatedRepository
import com.example.jetpack.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopRatedViewmodel @Inject constructor(private val repository: TopRatedRepository): BaseViewmodel() {

    private val _dataMovies = MutableLiveData<Result<TvMoviesResponse>>()
    val dataMovies: LiveData<Result<TvMoviesResponse>>
        get() = _dataMovies

    fun fetchTvMoviesData () {
        mCompositeDisposable += repository.fecthTopRatedMovies()
            .doOnSubscribe { setResult(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data ->
                    setResult(Result.HasData(data))
                },
                {error ->
                    setResult(Result.NoData())
                })
    }

    fun setResult(data: Result<TvMoviesResponse>){
        _dataMovies.postValue(data)
    }
}