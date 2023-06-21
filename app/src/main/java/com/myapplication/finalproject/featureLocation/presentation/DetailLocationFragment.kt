package com.myapplication.finalproject.featureLocation.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentDetailLocationBinding
import com.myapplication.finalproject.featureLocation.di.LocationsComponent


class DetailLocationFragment : BaseFragment<FragmentDetailLocationBinding,DetailLocationViewModel>(
    DetailLocationViewModel::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString("url")
        if (url!=null){
            println(url)
            viewModel.startLoadDetailLocation(url)
            viewModel.location.observe(requireActivity(), Observer {
                if (it!=null){
                    println(it)
                }
            })
        }
    }

    override fun createBinding(): FragmentDetailLocationBinding {
        return FragmentDetailLocationBinding.inflate(layoutInflater)
    }

    override fun initDaggerComponent(function: () -> Unit) {
        LocationsComponent.init(requireActivity()).inject(this)
    }
    companion object {
         @JvmStatic
        fun newInstance() = DetailLocationFragment()
    }
}