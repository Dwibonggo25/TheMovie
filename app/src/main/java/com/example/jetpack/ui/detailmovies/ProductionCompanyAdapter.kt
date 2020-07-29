package com.example.jetpack.ui.detailmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import com.example.jetpack.databinding.RvItemProductionCompanyBinding
import com.example.jetpack.model.NowPlayingData
import com.example.jetpack.model.ProductionCompany

class ProductionCompanyAdapter (val itemClick: (position:Int,model: ProductionCompany) -> Unit) : RecyclerView.Adapter<ProductionCompanyAdapter.Viewholder>() {

    private var items: List<ProductionCompany> = listOf()

    class Viewholder (val binding: RvItemProductionCompanyBinding ) :RecyclerView.ViewHolder (binding.root){

        fun bind (model : ProductionCompany) {
            binding.data = model
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemProductionCompanyBinding.inflate(inflater, parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) = holder.bind(items[position])

    fun refreshData(data : List<ProductionCompany>) {
        items = data
        notifyDataSetChanged()
    }
}