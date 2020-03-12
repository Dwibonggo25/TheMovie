package com.example.jetpack.ui.tvshows

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.R
import com.example.jetpack.Result
import com.example.jetpack.databinding.FragmentTvShowsBinding
import com.example.jetpack.model.ListTvMovies
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class TvShowsFragment : Fragment() {

    private lateinit var binding : FragmentTvShowsBinding

    private lateinit var viewmodel : TvShowsViewmodel

    private lateinit var adapter: TvShowsAdapter

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    companion object {
        fun newInstance() = TvShowsFragment ()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_shows, container, false)
        viewmodel = ViewModelProvider(this, viewModelFactory).get(TvShowsViewmodel::class.java)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewmodel.fetchTvMoviesData()

        viewmodel.dataMovies.observe(this, Observer {
            it?.let {
                when(it){
                    is Result.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is Result.HasData -> {
                        refreshData(it.data.results)
                        binding.progressbar.visibility = View.GONE
                    }
                    is Result.NoData ->{
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = TvShowsAdapter(listOf())
        binding.rvTvShows.adapter = adapter
    }

    fun refreshData(data: List<ListTvMovies>){
        adapter.refreshData(data)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}
