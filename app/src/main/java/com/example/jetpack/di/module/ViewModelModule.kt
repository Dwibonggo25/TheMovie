package com.example.jetpack.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.di.ViewModelFactory
import com.example.jetpack.di.ViewModelKey
import com.example.jetpack.ui.movies.MoviesViewmodel
import com.example.jetpack.ui.tvshows.TvShowsViewmodel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory : ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewmodel::class)
    internal abstract fun providesMoviesViewmodel(viewModel : MoviesViewmodel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowsViewmodel::class)
    internal abstract fun providesTvShowsViewmodel(viewModel : TvShowsViewmodel) : ViewModel

}