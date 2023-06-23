package com.myapplication.finalproject.featureLocation.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.databinding.ItemForLocationsBinding
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain


class AdapterForLocations: RecyclerView.Adapter<AdapterForLocations.ViewHolderForLocations>() {
    var list = ArrayList<LocationDomain>()
        set(value) {
            val callback = LocationDiffUtilCallback(list,value)
            val dif_result = DiffUtil.calculateDiff(callback)
            dif_result.dispatchUpdatesTo(this)
            field = value
        }
    var onClickListener:onClickItemLocationListener? =null
    class ViewHolderForLocations(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemForLocationsBinding.bind(item)
        fun bind(item: LocationDomain){
            binding.nameLocation.text = item.name
            binding.typeLocation.text = item.type
            binding.dimesionLocation.text= item.dimension
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForLocations {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_for_locations,parent,false)
        return ViewHolderForLocations(view)
    }

    override fun onBindViewHolder(holder: ViewHolderForLocations, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener?.clickitem(list.get(position))
        }
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
interface onClickItemLocationListener{
    fun clickitem(item: LocationDomain)
}
