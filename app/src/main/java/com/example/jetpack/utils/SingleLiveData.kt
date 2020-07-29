package com.example.jetpack.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner,
            Observer { data ->
                if (data == null) return@Observer
                observer.onChanged(data)
                value = null
            })
    }


    @MainThread
    fun sendAction(data: T) {
        value = data
    }

    fun sendActionOnBackground(data : T) {
        postValue(data)
    }

}