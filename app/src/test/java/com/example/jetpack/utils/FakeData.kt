package com.example.jetpack.utils

import com.example.jetpack.model.ListMovies

object FakeData {

    fun fakeDataDumyMovie() : List<ListMovies>{
        return listOf(
            ListMovies(true,"",1,"","","",0.0,"","","",true,0.0,0)
        )
    }
}