package com.myapplication.finalproject.featureEpisodes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentDetailEpisodeBinding
import com.myapplication.finalproject.featureEpisodes.di.EpisodesComponent


class DetailEpisodeFragment : BaseFragment<FragmentDetailEpisodeBinding,DetailEpisodeViewModel>(
    DetailEpisodeViewModel::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString("url")
        if (url!=null){
            println(url)
            viewModel.startLoadDetailEpisode(url)
            viewModel.episode.observe(requireActivity(), Observer {
                if (it!=null){
                    println(it)
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailEpisodeFragment()
    }

    override fun createBinding(): FragmentDetailEpisodeBinding {
        return FragmentDetailEpisodeBinding.inflate(layoutInflater)
    }

    override fun initDaggerComponent(function: () -> Unit) {
        EpisodesComponent.init(requireActivity()).inject(this)
    }
}