package com.myapplication.finalproject.featureChararcters.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain

class CharacterDiffUtilCallback(
private val oldList:List<CharacterDomain>,
private val newList:List<CharacterDomain>
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