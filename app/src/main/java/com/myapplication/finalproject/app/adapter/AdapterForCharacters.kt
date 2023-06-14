package com.myapplication.finalproject.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.databinding.ItemForChatacterBinding

import com.myapplication.finalproject.domain.models.CharacterDomain

class AdapterForCharacters:RecyclerView.Adapter<AdapterForCharacters.ViewHolderForCharacters>() {
    val list = ArrayList<CharacterDomain>()
    class ViewHolderForCharacters(item:View):RecyclerView.ViewHolder(item) {
        private val binding = ItemForChatacterBinding.bind(item)
            fun bind(item:CharacterDomain){
                binding.tvName.text = item.name.toString()
                binding.textView6.text = item.species.toString()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForCharacters {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_for_chatacter,parent,false)
            return ViewHolderForCharacters(view)
    }

    override fun onBindViewHolder(holder: ViewHolderForCharacters, position: Int) {
            holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

}