package com.myapplication.finalproject.featureEpisodes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.databinding.ItemForEpisodesBinding
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain

class AdapterForEpisodes : RecyclerView.Adapter<AdapterForEpisodes.ViewHolderForEpisodes>() {
    var list = ArrayList<EpisodeDomain>()
        set(value) {
            val callback = EpisodeDiffUtillCallback(list,value)
            val dif_result = DiffUtil.calculateDiff(callback)
            dif_result.dispatchUpdatesTo(this)
            field = value
        }
    var onClickListener:onClickItemEpisodeListener? =null
    class ViewHolderForEpisodes(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemForEpisodesBinding.bind(item)
        fun bind(item: EpisodeDomain){
            binding.nameEpisode.text = item.name
            binding.numberEpisode.text = item.episode
            binding.airDate.text = item.airDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForEpisodes {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_for_episodes,parent,false)
        return ViewHolderForEpisodes(view)
    }

    override fun onBindViewHolder(holder: ViewHolderForEpisodes, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener?.clickitem(list.get(position))
        }
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
interface onClickItemEpisodeListener{
    fun clickitem(item: EpisodeDomain)
}
