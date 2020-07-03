package com.example.jetpack.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.RvItemMoviesBinding
import com.example.jetpack.model.ListMovies

class PopularMoviesAdapter (var listMovies : List<ListMovies>) : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemMoviesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position], position)
    }

    class ViewHolder(private val binding: RvItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ListMovies, position: Int) {
            binding.movies = model
            binding.executePendingBindings()
        }
    }

    fun refreshData(data : List<ListMovies>) {
        this.listMovies = data
        notifyDataSetChanged()
    }
}