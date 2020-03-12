package com.example.jetpack.di.module

import com.example.jetpack.ui.LoginFragment
import com.example.jetpack.ui.home.HomeFragment
import com.example.jetpack.ui.movies.MoviesFragment
import com.example.jetpack.ui.splashscreen.SplashScreenFragment
import com.example.jetpack.ui.tvshows.TvShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributesSplashScreenFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributesTvShowsFragment(): TvShowsFragment
}