package com.example.jetpack

sealed class Result <out T> {

    class Loading <out T> : Result<T>()
    class NoData<out T> : Result<T>()
    class HasData<out T>(val data : T) : Result<T>()
}