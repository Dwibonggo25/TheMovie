package com.example.jetpack.di.component

import com.example.jetpack.SimpleApp
import com.example.jetpack.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        NetworkModule::class,
        BuilderModule::class,
        AppModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        FragmentModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: SimpleApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: SimpleApp)
}