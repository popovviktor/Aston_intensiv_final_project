package com.myapplication.finalproject.app.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.BaseFragment
import com.myapplication.finalproject.databinding.FragmentCharactersBinding


class CharactersFragment :BaseFragment<FragmentCharactersBinding,CharactersViewModel>(
CharactersViewModel::class.java
){
    override fun createBinding(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("ssss")
    }

}