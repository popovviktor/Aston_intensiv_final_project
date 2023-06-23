package com.myapplication.finalproject.featureChararcters.presentation.adapter

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.databinding.ItemForChatacterBinding

import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.squareup.picasso.Picasso

class AdapterForCharacters:RecyclerView.Adapter<AdapterForCharacters.ViewHolderForCharacters>() {
    var list = ArrayList<CharacterDomain>()
        set(value) {
            val callback = CharacterDiffUtilCallback(list,value)
            val dif_result = DiffUtil.calculateDiff(callback)
            dif_result.dispatchUpdatesTo(this)
            field = value
        }
    var onClickListener:onClickItemCharacterListener? =null
    class ViewHolderForCharacters(item:View):RecyclerView.ViewHolder(item) {
        private val binding = ItemForChatacterBinding.bind(item)
            fun bind(item: CharacterDomain){
                binding.TvName.text = item.name.toString()
                binding.TvSpecies.text = item.species.toString()
                binding.TvStatusItem.text = item.status.toString()
                binding.tvItemGender.text = item.gender.toString()
                Picasso.get().load(item.image).into(binding.imViewItemChatacter);
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForCharacters {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_for_chatacter,parent,false)
            return ViewHolderForCharacters(view)
    }

    override fun onBindViewHolder(holder: ViewHolderForCharacters, position: Int) {
            holder.itemView.setOnClickListener {
                onClickListener?.clickitem(list.get(position))
            }
            holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
interface onClickItemCharacterListener{
    fun clickitem(item: CharacterDomain)
}