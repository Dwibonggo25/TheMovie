package com.example.jetpack.di.module

import com.example.jetpack.ui.home.HomeFragment
import com.example.jetpack.ui.popular.PopularMoviesFragment
import com.example.jetpack.ui.toprated.TopRatedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesMoviesFragment(): PopularMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributesTvShowsFragment(): TopRatedFragment

}