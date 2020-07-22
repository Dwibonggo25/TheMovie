package com.example.jetpack.ui.favorite

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
import com.example.jetpack.databinding.FragmentFavoriteBinding
import com.example.jetpack.db.Favorite
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : FavoriteViewmodel

    private lateinit var binding : FragmentFavoriteBinding

    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.apply {
            executePendingBindings()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleview()
        viewModel.fetchAllDataInFavorite()

        viewModel.dataMovies.observe(this, Observer {
            it?.let {
                when(it){
                    is Result.HasData ->{
                        refreshAdapter(it.data)
                    }

                }
            }
        })
    }

    private fun initRecycleview (){
        adapter = FavoriteAdapter(listOf())
        binding.rvFavorite.adapter = adapter
    }

    private fun refreshAdapter(data: List<Favorite>){
        adapter.refreshData(data)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}