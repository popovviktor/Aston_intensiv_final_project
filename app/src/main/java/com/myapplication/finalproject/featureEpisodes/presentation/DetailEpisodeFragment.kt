package com.myapplication.finalproject.featureEpisodes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentDetailEpisodeBinding
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.presentation.DetailCharacterFragment
import com.myapplication.finalproject.featureChararcters.presentation.adapter.AdapterForCharacters
import com.myapplication.finalproject.featureChararcters.presentation.adapter.SpaceItemDecorationCharacters
import com.myapplication.finalproject.featureChararcters.presentation.adapter.onClickItemCharacterListener
import com.myapplication.finalproject.featureEpisodes.di.EpisodesComponent
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain

private const val LANDSCAPE_ORIENTATION = 2
class DetailEpisodeFragment : BaseFragment<FragmentDetailEpisodeBinding,DetailEpisodeViewModel>(
    DetailEpisodeViewModel::class.java
),onClickItemCharacterListener {
    private val adapterForCharacters = AdapterForCharacters()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerAdapter()
        val activity = requireActivity() as MainActivity
        activity.setVisibleBackButton()
        val url = arguments?.getString("url")
        if (url!=null){
            startLoadDetailEpisode(url)
            followLoadEndDetailEpisode()
        }
    }
    fun initRecyclerAdapter(){
        val orientation = requireActivity().resources.configuration.orientation
        var spanCountForGridLayout = 2
        if (orientation== LANDSCAPE_ORIENTATION){
            spanCountForGridLayout = 3
        }
        binding.rvForCharactersDetailEpisode.layoutManager = GridLayoutManager(activity,
            spanCountForGridLayout, RecyclerView.VERTICAL,false)
        binding.rvForCharactersDetailEpisode.addItemDecoration(SpaceItemDecorationCharacters(5))
        binding.rvForCharactersDetailEpisode.adapter = adapterForCharacters
        adapterForCharacters.onClickListener = this
    }
    fun generateUrlForLoadCharacters():String{
        val defUrlEpisode = "https://rickandmortyapi.com/api/character/"
        val urlStringBuilder = StringBuilder().append(defUrlEpisode)
        for (elem in viewModel.episode.value!!.characters){
            val urlThisEpisode = elem
            val indexUrlThisEpisode = urlThisEpisode.replace(defUrlEpisode,"")
            urlStringBuilder.append(indexUrlThisEpisode)
            urlStringBuilder.append(",")
        }
        return urlStringBuilder.trimEnd(',').toString()
    }
    fun startLoadCharacters(){
        val urlForLoad = generateUrlForLoadCharacters()
        viewModel.startLoadCharacters(urlForLoad)
    }
    fun followLoadEndCharacters(){
        viewModel.characters.observe(requireActivity(), Observer {
            if (it!=null){
                adapterForCharacters.list = it
                adapterForCharacters.notifyDataSetChanged()
            }
        })
    }

    fun startLoadDetailEpisode(url:String){
        viewModel.startLoadDetailEpisode(url)
    }
    fun followLoadEndDetailEpisode(){
        viewModel.episode.observe(requireActivity(), Observer {
            if (it!=null){
                updateUi(it)
                startLoadCharacters()
                followLoadEndCharacters()
            }
        })
    }
    fun updateUi(episode:EpisodeDomain){
        binding.tvEpisodeEpisode.text = episode.episode
        binding.tvNameEpisode.text = episode.name
        binding.tvAirDateEpisode.text = episode.airDate
        binding.progressNewPageDetailEpisode.visibility = View.GONE
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

    override fun clickitem(item: CharacterDomain) {
        val detailFragment = DetailCharacterFragment()
        val bundleToDetail = Bundle()
        bundleToDetail.putString("url",item.url)
        detailFragment.arguments = bundleToDetail
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,detailFragment)
            .addToBackStack(null)
            .commit()
    }
}