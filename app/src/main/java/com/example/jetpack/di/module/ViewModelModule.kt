package com.example.jetpack.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.di.ViewModelFactory
import com.example.jetpack.di.ViewModelKey
import com.example.jetpack.ui.detailmovies.DetailMoviesViewmodel
import com.example.jetpack.ui.home.HomeViewModel
import com.example.jetpack.ui.popular.PopularMoviesViewmodel
import com.example.jetpack.ui.toprated.TopRatedViewmodel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory : ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewmodel::class)
    internal abstract fun providesMoviesViewmodel(viewModel : PopularMoviesViewmodel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedViewmodel::class)
    internal abstract fun providesTvShowsViewmodel(viewModel : TopRatedViewmodel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun providesHomeViewModel(viewModel : HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMoviesViewmodel::class)
    internal abstract fun providesDetailMoviesViewmodel(viewModel : DetailMoviesViewmodel) : ViewModel
}