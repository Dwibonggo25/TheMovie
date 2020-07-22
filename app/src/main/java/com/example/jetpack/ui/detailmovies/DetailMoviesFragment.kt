package com.example.jetpack.ui.detailmovies

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
import com.example.jetpack.databinding.FragmentDetailMoviesBinding
import com.example.jetpack.model.ProductionCompany
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fab_layout.view.*
import javax.inject.Inject

class DetailMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : DetailMoviesViewmodel

    private lateinit var binding : FragmentDetailMoviesBinding

    private lateinit var titleToolbar : OnSetTitleToollbar

    private lateinit var companyAdapter: ProductionCompanyAdapter

    private var movieId: String = ""

    var isRotate: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailMoviesViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movies, container, false)
        binding.apply {
            vm = viewModel
            fragment = this@DetailMoviesFragment
            executePendingBindings()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setTitleToolbarListener ()
        initCompanyRecyclerView()

        movieId = DetailMoviesFragmentArgs.fromBundle(arguments).movieId
        viewModel.viewSvaeDataFavoriteMovies(movieId.toLong())

        viewModel.fetchMovieData(movieId)
        viewModel.dataMovies.observe(this, Observer {
            it.let {
                when(it) {
                    is Result.Loading ->{
                        showShimmerLoading()
                    }
                    is Result.HasData -> {
                        titleToolbar.getTitleToollbar(it.data.title)
                        binding.detailShow = true
                        refreshCompanyData(it.data.production_companies)
                    }
                }
            }
        })
    }

    private fun setTitleToolbarListener() {
        try {
            titleToolbar = activity as OnSetTitleToollbar
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString() + " must implement OnHeadlineSelectedListener"
            )
        }
    }

    private fun showShimmerLoading() {
        binding.detailShow = false
        binding.shimmerDetailMovies.startShimmer()
    }

    private fun refreshCompanyData (data: List<ProductionCompany>) {
        companyAdapter.refreshData(data)
    }

    private fun initCompanyRecyclerView() {
        companyAdapter = ProductionCompanyAdapter(listOf())
        binding.rvCompany.adapter = companyAdapter
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun onFavoriteButtonClick() {
        viewModel.saveDataFavoriteMovies()
        isRotate = rotateFab(binding.floatingActionButton, !isRotate)
    }

    interface OnSetTitleToollbar {
        fun getTitleToollbar(title: String)
    }

    fun rotateFab(view: View, rotate: Boolean): Boolean {
        view.animate().setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                }
            })
        return rotate
    }
}

