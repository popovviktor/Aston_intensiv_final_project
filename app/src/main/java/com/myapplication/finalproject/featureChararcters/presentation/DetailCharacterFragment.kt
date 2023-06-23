package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentDetailCharacterBinding
import com.myapplication.finalproject.featureChararcters.di.CharactersComponent
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.presentation.DetailEpisodeFragment
import com.myapplication.finalproject.featureEpisodes.presentation.adapter.AdapterForEpisodes
import com.myapplication.finalproject.featureEpisodes.presentation.adapter.onClickItemEpisodeListener
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.presentation.DetailLocationFragment
import com.myapplication.finalproject.featureLocation.presentation.adapter.AdapterForLocations
import com.myapplication.finalproject.featureLocation.presentation.adapter.SpaceItemDecorationLocations
import com.myapplication.finalproject.featureLocation.presentation.adapter.onClickItemLocationListener
import com.squareup.picasso.Picasso

class DetailCharacterFragment : BaseFragment<FragmentDetailCharacterBinding,DetailCharacterViewModel>
    (DetailCharacterViewModel::class.java),onClickItemLocationListener,onClickItemEpisodeListener{
    private val adapterForOrigin = AdapterForLocations()
    private val adapterForLocation = AdapterForLocations()
    private val adapterForEpisode = AdapterForEpisodes()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString("url")
        val activity = requireActivity() as MainActivity
        activity.setVisibleBackButton()
        initSettingForAdapterLocation()
        initSettingForAdapterOrigin()
        initSettingForAdapterEpisodes()
        if (url!=null){
            viewModel.startLoadDetailCharacter(url)
            followLoadDetail()
        }
    }

    fun followLoadDetail(){
        viewModel.character.observe(requireActivity(), Observer {
            if (it!=null){
                insertDetailCharacterUI(it)
                startLoadOriginAndLocaion()
                followOriginAndLocation()
                startLoadEpisodes()
                followLoadEpisodes()
            }
        })
    }
    fun initSettingForAdapterEpisodes(){
        binding.rvForEpisodesDetailCharacter.layoutManager= GridLayoutManager(activity,
            1, RecyclerView.VERTICAL,false)
        adapterForEpisode.onClickListener = this
        binding.rvForEpisodesDetailCharacter.adapter = adapterForEpisode
        binding.rvForEpisodesDetailCharacter.addItemDecoration(SpaceItemDecorationLocations(5))
    }
    fun initSettingForAdapterLocation(){
        binding.rvForLocationDetailCharacter.layoutManager = GridLayoutManager(activity,
            1, RecyclerView.VERTICAL,false)
        adapterForLocation.onClickListener = this
        binding.rvForLocationDetailCharacter.adapter = adapterForLocation
        binding.rvForLocationDetailCharacter.addItemDecoration(SpaceItemDecorationLocations(5))
    }
    fun initSettingForAdapterOrigin(){
        binding.rvForOriginDetailCharacter.layoutManager = GridLayoutManager(activity,
            1, RecyclerView.VERTICAL,false)
        adapterForOrigin.onClickListener = this
        binding.rvForOriginDetailCharacter.adapter = adapterForOrigin
        binding.rvForOriginDetailCharacter.addItemDecoration(SpaceItemDecorationLocations(5))

    }
    fun insertDetailCharacterUI(character:CharacterDomain){
        Picasso.get().load(character.image).into(binding.imViewDetailCharacter)
        binding.tvNameDetailCharacter.text = character.name
        binding.tvSpeciesCharacter.text = character.species
        binding.tvTypeCharacter.text = character.type
        binding.tvStatusCharacter.text = character.status
        binding.tvGenderCharacter.text = character.gender
        binding.progressNewPageDetailCharacter.visibility = View.GONE
    }
    fun generateUrlForLoadAllEpisode():String{
        val defUrlEpisode = "https://rickandmortyapi.com/api/episode/"
        val urlStringBuilder = StringBuilder().append(defUrlEpisode)
        for (elem in viewModel.character.value!!.episode){
            val urlThisEpisode = elem
            val indexUrlThisEpisode = urlThisEpisode.replace(defUrlEpisode,"")
            urlStringBuilder.append(indexUrlThisEpisode)
            urlStringBuilder.append(",")
        }
        return urlStringBuilder.trimEnd(',').toString()
    }
    fun startLoadEpisodes(){
        val urlEpisoed = generateUrlForLoadAllEpisode()
        viewModel.startLoadEpisodes(urlEpisoed)
    }
    fun followLoadEpisodes(){
        viewModel.episodes.observe(requireActivity(), Observer {
            if (it!=null){
                adapterForEpisode.list=it
                adapterForEpisode.notifyDataSetChanged()
            }
        })
    }
    fun startLoadOriginAndLocaion(){
        if (viewModel.character.value?.origin?.url?.length!! <2){
            binding.originUnknown.visibility = View.VISIBLE
        }
        viewModel.startLoadOrigin()
        viewModel.startLoadLocation()
    }
    fun followOriginAndLocation(){
        viewModel.origin.observe(requireActivity(), Observer {
            if (it!=null){
                var arrayOrigins = ArrayList<LocationDomain>()
                arrayOrigins.add(it)
                adapterForOrigin.list=arrayOrigins
                adapterForOrigin.notifyDataSetChanged()
            }
        })
        viewModel.location.observe(requireActivity(), Observer {
            if (it!=null){
                var arrayLocations = ArrayList<LocationDomain>()
                arrayLocations.add(it)
                adapterForLocation.list = arrayLocations
                adapterForLocation.notifyDataSetChanged()
            }
        })
    }
    override fun createBinding(): FragmentDetailCharacterBinding {
        return FragmentDetailCharacterBinding.inflate(layoutInflater)
    }

    override fun initDaggerComponent(function: () -> Unit) {
        CharactersComponent.init(requireActivity()).inject(this)
    }

    override fun clickitem(item: LocationDomain) {
        val detailFragment = DetailLocationFragment()
        val bundleToDetail = Bundle()
        bundleToDetail.putString("url",item.url)
        detailFragment.arguments = bundleToDetail
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun clickitem(item: EpisodeDomain) {
        val detailFragment = DetailEpisodeFragment()
        val bundleToDetail = Bundle()
        bundleToDetail.putString("url",item.url)
        detailFragment.arguments = bundleToDetail
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }


}