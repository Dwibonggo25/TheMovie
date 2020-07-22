package com.example.jetpack.ui.popular

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R
import com.example.jetpack.Result
import com.example.jetpack.databinding.FragmentPopularMoviesBinding
import com.example.jetpack.model.ListMovies
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PopularMoviesFragment : Fragment() , PopularMoviesAdapter.OnPuplarMoviesClick{

    private lateinit var binding : FragmentPopularMoviesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : PopularMoviesViewmodel

    private lateinit var adapter: PopularMoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this, viewModelFactory).get(PopularMoviesViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular_movies, container, false)
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
        adapter = PopularMoviesAdapter(listOf(), this)
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

    override fun onItemMoviesClcik(movieId: String) {
        val action = PopularMoviesFragmentDirections.actionPopularToDetailMoviesFragmentLaunch(movieId)
        findNavController().navigate(action)
    }
}
