package com.example.jetpack.ui.movies

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R
import com.example.jetpack.Result
import com.example.jetpack.databinding.FragmentMoviesBinding
import com.example.jetpack.model.ListMovies
import com.example.jetpack.ui.LoginViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MoviesFragment : Fragment() {

    private lateinit var binding : FragmentMoviesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : MoviesViewmodel

    private lateinit var adapter: MoviesAdapter

    companion object {
        fun newInstance() = MoviesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDataMovies()

        initRecyclerView()

        viewModel.dataMovies.observe(this, Observer {
            it?.let {
                when(it){
                    is Result.Loading ->{
                       binding.progressbar.visibility = View.VISIBLE

                    }
                    is Result.HasData -> {
                        refreshAdapter(it.data.results)
                        binding.progressbar.visibility = View.GONE
                    }
                    is Result.NoData -> {
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = MoviesAdapter(listOf())
        val layoutManager = LinearLayoutManager(activity)
        binding.rvMoviesPopular.layoutManager = layoutManager
        binding.rvMoviesPopular.adapter = adapter
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun refreshAdapter(data: List<ListMovies>){
        adapter.refreshData(data)
    }
}
