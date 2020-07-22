package com.example.jetpack.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack.Result
import com.example.jetpack.base.BaseViewmodel
import com.example.jetpack.db.Favorite
import com.example.jetpack.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class FavoriteViewmodel @Inject constructor(private val repository: FavoriteRepository) : BaseViewmodel() {

    private var _dataMovies = MutableLiveData<Result<List<Favorite>>>()
    val dataMovies: LiveData<Result<List<Favorite>>>
        get() = _dataMovies

    fun fetchAllDataInFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = repository.fetchAllDataInFavorite()
                if (!data.isNullOrEmpty()) {
                    setResult(Result.HasData(data))
                } else {
                    setResult(Result.NoData())
                }
            } catch (e: Exception) {
                setResult(Result.Error(e.message))
            }
        }
    }

    private fun setResult(data : Result<List<Favorite>>){
        _dataMovies.postValue(data)
    }
}