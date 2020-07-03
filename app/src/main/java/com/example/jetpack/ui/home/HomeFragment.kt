package com.example.jetpack.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.jetpack.R
import com.example.jetpack.Result
import com.example.jetpack.databinding.FragmentHomeBinding
import com.example.jetpack.model.NowPlayingData
import com.example.jetpack.model.UpcomingData
import com.example.jetpack.ui.toprated.TopRatedAdapter
import com.example.jetpack.ui.toprated.TopRatedViewmodel
import com.example.jetpack.utils.plusAssign
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    private lateinit var viewmodel : HomeViewModel
    private lateinit var adapter: NowPlayingAdapter
    private lateinit var upcommingAdapter : HomeMoviesAdapter
    private lateinit var viewPager: ViewPager
    private val compositeDisposable = CompositeDisposable()

    companion object {
        const val SWIPE_INTERVAL = 8000L
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewmodel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.apply {
            fragment = this@HomeFragment
            executePendingBindings()
        }
        return binding.root
    }

    fun onFavoriteFilmClick() {
        val action = HomeFragmentDirections.popularMoviesFragmentLaunch()
        findNavController().navigate(action)
    }

    fun onTopRatedFilmClick() {
        val action = HomeFragmentDirections.topRatedMoviesFragmentLaunch()
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.fetchNowPlayingMovies()
        viewmodel.fetchUpcomingMovies()

        initUpcomingMovies()
        initRecyclerview()
        viewmodel.dataMovies.observe(this, Observer {
                it.let {
                    when (it){
                        is Result.Loading ->{
                            showShimmerNowPlaying()
                        }
                        is Result.HasData -> {
                            showDataNowPlaying()
                            refreshAdapter(it.data.results)
                        }
                    }
                }

            }
        )

        viewmodel.upcomingMovies.observe(this, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {

                    }
                    is Result.HasData -> {
                        refreshUpcomingAdapter(it.data.results)
                        initUpcomingSwipeScheduler()
                    }
                }
            }
        })
    }

    private fun initUpcomingMovies() {
        viewPager = binding.contentHeaderMovies.viewPager
        val tabLayout = binding.contentHeaderMovies.tablayout
        upcommingAdapter = HomeMoviesAdapter(listOf(), requireActivity())
        viewPager.adapter = upcommingAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    private fun initUpcomingSwipeScheduler() {
        compositeDisposable += Observable.interval(SWIPE_INTERVAL, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Long>() {
                override fun onComplete() {

                }

                override fun onNext(t: Long) {

                    val currentViewpagerPosition = viewPager.currentItem
                    val headlineBannerSize =upcommingAdapter.count
                    if (currentViewpagerPosition < headlineBannerSize - 1) {
                        viewPager.setCurrentItem(currentViewpagerPosition + 1, true)
                    } else {
                        viewPager.setCurrentItem(0, true)
                    }
                }

                override fun onError(e: Throwable) {

                }
            })
    }

    private fun showDataNowPlaying() {
        binding.showNowPlaying = true
    }

    private fun showShimmerNowPlaying() {
        binding.showNowPlaying = false
        binding.shimmerNowPlaying.startShimmer()
    }

    private fun initRecyclerview() {
        adapter = NowPlayingAdapter(listOf())
        binding.rvNowPlaying.adapter = adapter
    }

    private fun refreshUpcomingAdapter (data : List<UpcomingData>) {
        upcommingAdapter.refreshHeadlines(data)
    }

    private fun refreshAdapter (data : List<NowPlayingData>) {
        adapter.refreshData(data)
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
