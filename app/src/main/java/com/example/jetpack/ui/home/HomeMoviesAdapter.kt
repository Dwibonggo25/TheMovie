package com.example.jetpack.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpack.BASE_IMAGE_URL
import com.example.jetpack.R
import com.example.jetpack.model.UpcomingData

class HomeMoviesAdapter(var movies : List<UpcomingData>, val context: Context) : PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int = movies.size

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val view = obj as View
        container.removeView(view)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movies, container, false)
        val imageView = view.findViewById<ImageView>(R.id.ivImageMovies)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDateRelease = view.findViewById<TextView>(R.id.tvDateRelease)
        val tvVote = view.findViewById<TextView>(R.id.tvVote)
        val tvLikes = view.findViewById<TextView>(R.id.tvLikes)

        val vote = movies[position].vote_average.toString()
        val likes = movies[position].popularity.toString()

        tvTitle.setText(movies[position].title)
        tvDateRelease.setText(movies[position].release_date)
        tvVote.setText("$vote K")
        tvLikes.setText(likes)

        val moviesImage = movies[position].backdrop_path
        val options = RequestOptions.noTransformation()

        val uri = "$BASE_IMAGE_URL$moviesImage"

        Glide.with(context)
            .load(uri)
            .apply(options)
            .into(imageView)

        container.addView(view, 0)
        return view
    }

    fun refreshHeadlines(movies: List<UpcomingData>) {
        this.movies = movies
        notifyDataSetChanged()
    }

}