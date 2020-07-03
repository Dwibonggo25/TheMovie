package com.example.jetpack.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingexpert2.RxImmediateSchedulerRule
import com.example.jetpack.model.ListMovies
import com.example.jetpack.model.MoviesResponse
import com.example.jetpack.repository.PopularMoviesRepository
import com.example.jetpack.ui.popular.PopularMoviesViewmodel
import com.example.jetpack.utils.FakeData
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewmodelTest {

    private val fakeData = FakeData.fakeDataDumyMovie()[0]
    
    @Mock
    lateinit var viewmodel: PopularMoviesViewmodel

    @Mock
    lateinit var repository: PopularMoviesRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewmodel = PopularMoviesViewmodel(repository)
    }

    private val listMovies = MoviesResponse(0, listOf(ListMovies(true,"",1,"","","",0.0,"","","",true,0.0,0)),0,0)

    @Test
    fun fetchMovieData(){
        Mockito.`when`(repository.fetchPopularMovie()).thenReturn(Observable.just(listMovies))
        viewmodel.fetchDataMovies()
        with(viewmodel){
            Mockito.verify(repository).fetchPopularMovie()
        }

    }
}