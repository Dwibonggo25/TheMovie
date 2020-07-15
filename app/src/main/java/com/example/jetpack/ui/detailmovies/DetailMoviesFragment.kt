package com.example.jetpack.ui.detailmovies

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentDetailMoviesBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : DetailMoviesViewmodel

    private lateinit var binding : FragmentDetailMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailMoviesViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movies, container, false)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = DetailMoviesFragmentArgs.fromBundle(arguments).movieId
        viewModel.fetchMovieData(movieId)
        viewModel.dataMovies.observe(this, Observer {
            it.let {

            }
        })
    }
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
