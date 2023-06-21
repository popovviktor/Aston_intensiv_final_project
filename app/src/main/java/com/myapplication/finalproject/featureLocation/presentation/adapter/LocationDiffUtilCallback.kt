package com.myapplication.finalproject.featureLocation.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain

class LocationDiffUtilCallback(
    private val oldList:List<LocationDomain>,
    private val newList:List<LocationDomain>
):DiffUtil.Callback() {
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