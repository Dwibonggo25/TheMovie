package com.example.jetpack.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack.Result
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.model.MoviesResponse
import com.example.jetpack.model.TvMoviesResponse
import com.example.jetpack.repository.MoviesRepository
import com.example.jetpack.repository.TvShowsRepository
import com.example.jetpack.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvShowsViewmodel @Inject constructor(private val repository: TvShowsRepository): BaseViewmodel() {

    private val _dataMovies = MutableLiveData<Result<TvMoviesResponse>>()
    val dataMovies: LiveData<Result<TvMoviesResponse>>
        get() = _dataMovies

    fun fetchTvMoviesData () {
        mCompositeDisposable += repository.fecthTvMoviesData()
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