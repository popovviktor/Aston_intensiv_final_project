package com.myapplication.finalproject.featureEpisodes.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain

class EpisodeDiffUtillCallback (
    private val oldList:List<EpisodeDomain>,
    private val newList:List<EpisodeDomain>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val olditem = oldList.get(oldItemPosition)
        val newitem = newList.get(newItemPosition)
        return olditem.id == newitem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val olditem = oldList.get(oldItemPosition)
        val newitem = newList.get(newItemPosition)
        return olditem == newitem
    }
}