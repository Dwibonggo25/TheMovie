package com.example.jetpack.ui.detailmovies

import android.animation.AnimatorListenerAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack.Result
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.db.Favorite
import com.example.jetpack.model.DetailMoviesResponse
import com.example.jetpack.repository.DetailMoviesRepository
import com.example.jetpack.utils.getYearFromDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailMoviesViewmodel @Inject constructor(private val repository: DetailMoviesRepository) : BaseViewmodel() {

    private val _dataMovies = MutableLiveData<Result<DetailMoviesResponse>>()
    val dataMovies: LiveData<Result<DetailMoviesResponse>>
        get() = _dataMovies

    val movies = ObservableField<DetailMoviesResponse>()
    val titleMovies = ObservableField<String>()
    val viewers =  ObservableField<String>()

    val isFavorite = ObservableField<Boolean>(false)

    fun fetchMovieData (movieId: String) {
        viewModelScope.launch (Dispatchers.IO) {
            val data = repository.fetchDetailMovies(movieId)
            try {
                movies.set(data)
                titleMovies.set("${data.title} (${data.release_date.getYearFromDate()})")
                viewers.set("${data.vote_average}x jt ditonton")
                setResult(Result.HasData(data))
            }catch (e : Exception) {
                setResult(Result.Error(e.message))
            }
        }
    }

    fun viewSvaeDataFavoriteMovies(id: Long){
        viewModelScope.launch (Dispatchers.IO) {
            try {
                var data  = repository.fetchMoviesInFavorite(id)
                if (data > 0) {
                    isFavorite.set(true)
                }else{
                    isFavorite.set(false)
                }
            }catch (e: Exception) {
                isFavorite.set(false)
            }
        }

    }


    fun saveDataFavoriteMovies () {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var genre = movies.get()!!.genres.get(0).name
                if(isFavorite.get() != true){
                    isFavorite.set(true)
                    repository.saveDataFavorite(Favorite(movies.get()!!.id, movies.get()!!.title, movies.get()!!.release_date, movies.get()!!.poster_path, movies.get()!!.vote_average.toString(),movies.get()!!.original_title, movies.get()!!.overview, genre, 1))
                }else{
                    isFavorite.set(false)
                    repository.saveDataFavorite(Favorite(movies.get()!!.id, movies.get()!!.title, movies.get()!!.release_date, movies.get()!!.poster_path, movies.get()!!.vote_average.toString(),movies.get()!!.original_title, movies.get()!!.overview, genre,0))
                }
            }catch (e: Exception) {

            }
        }
    }

    fun setResult (data : Result<DetailMoviesResponse>){
        _dataMovies.postValue(data)
    }
}