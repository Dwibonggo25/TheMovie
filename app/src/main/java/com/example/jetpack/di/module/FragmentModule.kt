package com.example.jetpack.di.module

import com.example.jetpack.ui.LoginFragment
import com.example.jetpack.ui.splashscreen.SplashScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributesSplashScreenFragment(): SplashScreenFragment
}