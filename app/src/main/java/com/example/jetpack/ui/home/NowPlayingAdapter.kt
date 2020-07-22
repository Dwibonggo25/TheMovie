package com.example.jetpack.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.RvItemNowPlayingBinding
import com.example.jetpack.model.NowPlayingData

class NowPlayingAdapter (var data : List<NowPlayingData>, private val listener: OnNowPlayingPressed) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemNowPlayingBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }


    class ViewHolder(val binding: RvItemNowPlayingBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind (model: NowPlayingData, listener: OnNowPlayingPressed) {
            binding.movies = model

            binding.root.setOnClickListener {
                listener.onNowPlayingClicked(model.id.toString())
            }

            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    fun refreshData(data : List<NowPlayingData>) {
        this.data= data
        notifyDataSetChanged()
    }

    interface OnNowPlayingPressed {
        fun onNowPlayingClicked(movieId: String)
    }
}