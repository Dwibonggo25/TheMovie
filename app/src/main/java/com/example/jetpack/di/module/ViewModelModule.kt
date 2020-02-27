package com.example.jetpack.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.ui.LoginViewModel
import com.example.jetpack.di.ViewModelFactory
import com.example.jetpack.di.ViewModelKey
import com.example.jetpack.ui.splashscreen.SplashScreenViewmodel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory : ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun providesLoginViewModel(viewModel : LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewmodel::class)
    internal abstract fun providesSplashScreenViewmodel(viewModel : SplashScreenViewmodel) : ViewModel

}