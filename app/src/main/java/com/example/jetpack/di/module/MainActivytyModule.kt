package com.example.jetpack.di.module

import dagger.Provides
import com.example.jetpack.MainActivity
import dagger.Module


@Module
class MainActivityModule {

    @Provides
    internal fun provideMainView(mainActivity: MainActivity)= mainActivity

}