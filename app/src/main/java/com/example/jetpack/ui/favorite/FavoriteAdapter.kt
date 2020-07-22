package com.example.jetpack.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.RvItemFavoriteBinding
import com.example.jetpack.db.Favorite
import com.example.jetpack.model.NowPlayingData

class FavoriteAdapter (var data: List<Favorite>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder (val binding: RvItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Favorite){
            binding.movies = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemFavoriteBinding.inflate(inflater, parent, false)
        return FavoriteAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun refreshData(data : List<Favorite>) {
        this.data= data
        notifyDataSetChanged()
    }
}