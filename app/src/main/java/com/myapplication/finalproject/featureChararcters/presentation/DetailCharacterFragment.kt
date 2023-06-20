package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentDetailCharacterBinding
import com.myapplication.finalproject.featureChararcters.di.CharactersComponent


class DetailCharacterFragment : BaseFragment<FragmentDetailCharacterBinding,DetailCharacterViewModel>
    (DetailCharacterViewModel::class.java){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("COCOOCOC")
        binding.button.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }


    override fun createBinding(): FragmentDetailCharacterBinding {
        return FragmentDetailCharacterBinding.inflate(layoutInflater)
    }

    override fun initDaggerComponent(function: () -> Unit) {
        CharactersComponent.init(requireActivity()).inject(this)
    }


}