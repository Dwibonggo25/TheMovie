package com.example.jetpack.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentHomeBinding
import com.example.jetpack.ui.movies.MoviesFragment
import com.example.jetpack.ui.tvshows.TvShowsFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.executePendingBindings()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settinViewPager()
        settingTabLayout()
    }
    private fun settinViewPager() {
        val adapter = ViewPagerAdapter (this)
        binding.viewPager.adapter = adapter
    }

    private fun settingTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            when (position){
                0 -> tab.setText("Movie")
                1 -> tab.setText("Tv Shows")
            }
        }.attach()
    }

    class ViewPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int  = 2

        override fun createFragment(position: Int): Fragment {
            return when (position){
                0 -> MoviesFragment.newInstance()
                else -> TvShowsFragment.newInstance()
            }
        }
    }

}
