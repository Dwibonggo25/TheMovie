package com.example.jetpack.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.RvItemMoviesBinding
import com.example.jetpack.model.ListMovies

class PopularMoviesAdapter (private var listMovies : List<ListMovies>, private var listener: OnPuplarMoviesClick) : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemMoviesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position], position, listener)
    }

    class ViewHolder(private val binding: RvItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ListMovies, position: Int, listener: OnPuplarMoviesClick) {
            binding.movies = model

            binding.root.setOnClickListener {
                listener.onItemMoviesClcik(model.id.toString())

            }

            binding.executePendingBindings()

        }

    }

    fun refreshData(data : List<ListMovies>) {
        this.listMovies = data
        notifyDataSetChanged()
    }

    interface OnPuplarMoviesClick{
        fun onItemMoviesClcik(movieId: String)
    }
}