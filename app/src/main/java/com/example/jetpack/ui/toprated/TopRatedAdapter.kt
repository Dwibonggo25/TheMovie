package com.example.jetpack.ui.toprated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.RvItemTopRatedBinding
import com.example.jetpack.model.ListTvMovies

class TopRatedAdapter (var listMovies : List<ListTvMovies>, val listener: OnTopRatedPressed) : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemTopRatedBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position], position, listener)
    }

    class ViewHolder(private val binding: RvItemTopRatedBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ListTvMovies, position: Int, listener: OnTopRatedPressed) {
            binding.movies = model

            binding.root.setOnClickListener {
                listener.onTopRatedClicked(model.id.toString())
            }
            binding.executePendingBindings()
        }
    }

    fun refreshData(data : List<ListTvMovies>) {
        this.listMovies = data
        notifyDataSetChanged()
    }

    interface OnTopRatedPressed {
        fun onTopRatedClicked(movieId: String)
    }
}