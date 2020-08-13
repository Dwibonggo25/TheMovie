package com.example.jetpack.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.RvItemFavoriteBinding
import com.example.jetpack.db.Favorite

class FavoriteAdapter ( private var listener: OnFavoriteMoviesListener ) : ListAdapter<Favorite, FavoriteAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder (val binding: RvItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Favorite, listener: OnFavoriteMoviesListener){
            binding.movies = data
            binding.executePendingBindings()

            binding.cardFavoriteMovies.setOnClickListener {
                listener.onDetailMovieClick(data.id!!)
            }

            binding.cardFavoriteMovies.setOnClickListener {
                listener.onDeleteOnFavoriteClciked(data.id!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemFavoriteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Favorite>(){
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnFavoriteMoviesListener{
        fun onDetailMovieClick(id: Int)
        fun onDeleteOnFavoriteClciked(id: Int)
    }

}