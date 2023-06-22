package com.myapplication.finalproject.featureLocation.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentDetailLocationBinding
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.presentation.DetailCharacterFragment
import com.myapplication.finalproject.featureChararcters.presentation.adapter.AdapterForCharacters
import com.myapplication.finalproject.featureChararcters.presentation.adapter.SpaceItemDecorationCharacters
import com.myapplication.finalproject.featureChararcters.presentation.adapter.onClickItemCharacterListener
import com.myapplication.finalproject.featureLocation.di.LocationsComponent
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain

private const val LANDSCAPE_ORIENTATION = 2
class DetailLocationFragment : BaseFragment<FragmentDetailLocationBinding,DetailLocationViewModel>(
    DetailLocationViewModel::class.java
), onClickItemCharacterListener {
    private val adapterForCharacters = AdapterForCharacters()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerAdapter()
        val activity = requireActivity() as MainActivity
        activity.setVisibleBackButton()
        val url = arguments?.getString("url")
        if (url!=null){
            startLoadLocationDeteail(url)
            followLoadLocationDetail()
        }
    }
    fun initRecyclerAdapter(){
        val orientation = requireActivity().resources.configuration.orientation
        var spanCountForGridLayout = 2
        if (orientation== LANDSCAPE_ORIENTATION){
            spanCountForGridLayout = 3
        }
        binding.rvForCharactersLocationDetail.layoutManager = GridLayoutManager(activity,
            spanCountForGridLayout, RecyclerView.VERTICAL,false)
        binding.rvForCharactersLocationDetail.addItemDecoration(SpaceItemDecorationCharacters(5))
        binding.rvForCharactersLocationDetail.adapter = adapterForCharacters
        adapterForCharacters.onClickListener = this
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
    fun generateUrlForLoadCharacters():String{
        val defUrlEpisode = "https://rickandmortyapi.com/api/character/"
        val urlStringBuilder = StringBuilder().append(defUrlEpisode)
        for (elem in viewModel.location.value!!.residents){
            val urlThisEpisode = elem
            val indexUrlThisEpisode = urlThisEpisode.replace(defUrlEpisode,"")
            urlStringBuilder.append(indexUrlThisEpisode)
            urlStringBuilder.append(",")
        }
        return urlStringBuilder.trimEnd(',').toString()
    }
    fun startLoadLocationDeteail(url:String){
        viewModel.startLoadDetailLocation(url)
    }
    fun followLoadLocationDetail(){
        viewModel.location.observe(requireActivity(), Observer {
            if (it!=null){
                updateUi(it)
                startLoadCharacters()
                followLoadEndCharacters()
            }
        })
    }
    fun updateUi(location: LocationDomain){
        binding.tvNameLocationDetail.text = location.name
        binding.tvDimensionLocationDetail.text = location.dimension
        binding.tvTypeLocationDetail.text = location.type
        binding.progressNewPageDetailLocation.visibility = View.GONE
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