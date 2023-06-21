package com.myapplication.finalproject.featureLocation.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentLocationsBinding
import com.myapplication.finalproject.featureChararcters.presentation.*
import com.myapplication.finalproject.featureLocation.di.LocationsComponent
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.presentation.adapter.AdapterForLocations
import com.myapplication.finalproject.featureLocation.presentation.adapter.onClickItemLocationListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


private const val REQUEST_KEY_LOCATION = "request_key_find_filter_location"
private const val FILTER_NAME = "filter_name"
private const val LANDSCAPE_ORIENTATION = 2
private val adapterLocation = AdapterForLocations()

class LocationsFragment : BaseFragment<FragmentLocationsBinding,LocationsViewModel>(
    LocationsViewModel::class.java
),onClickItemLocationListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerFragmentResultListener()
        followCharactersUpdateForUi()
        if (savedInstanceState==null){
            if (adapterLocation.list.size<1){
                getLoadDefaultPageAndFollowCharacterUpdateForUi()
            }
        }
        setClickCloseFilter()
        followFilterEnable()
        followInformationToast()
    }
    fun followCharactersUpdateForUi(){
        defaultSettingForRecyclerCharacters()
        viewModel.locations.observe(requireActivity(), Observer {
            if (it!=null){
                adapterLocation.list= it.results!!
            }
        })
    }
    fun registerFragmentResultListener(){
        setFragmentResultListener(REQUEST_KEY_LOCATION) { key, bundle ->
            val filterName = bundle.getString(FILTER_NAME)
            binding.progressRefresh2.visibility = View.VISIBLE
            viewModel.enableFilterFind()
            viewModel.getDefaultUrlForFindWithFilter(filterName)
        }
    }


    fun getLoadDefaultPageAndFollowCharacterUpdateForUi(){
        followCharactersUpdateForUi()
        viewModel.getDefaultPage()

    }
    fun defaultSettingForRecyclerCharacters(){
        setLayoutManagerInRecycler()
        binding.rvForLocations.adapter = adapterLocation
        adapterLocation.onClickListener = this
        addBottomSheetForFilter()
        followVisibleEndProgressBarLoad()
        setScrollListenerForRecyclerCharacters()
    }
    fun setLayoutManagerInRecycler(){
        val orientation = requireActivity().resources.configuration.orientation
        var spanCountForGridLayout = 2
        if (orientation== LANDSCAPE_ORIENTATION){
            spanCountForGridLayout = 3
        }
        binding.rvForLocations.layoutManager = GridLayoutManager(activity,
            spanCountForGridLayout, RecyclerView.VERTICAL,false)
    }
    fun setScrollListenerForRecyclerCharacters(){
        val listener = object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE&&
                    binding.progressRefresh2.visibility==View.GONE && binding.progressNextPage2.visibility==View.GONE){
                    pullToRefresh()
                }
                if (!recyclerView.canScrollVertically(1)&&newState == RecyclerView.SCROLL_STATE_IDLE&&
                    binding.progressNextPage2.visibility==View.GONE&&binding.progressRefresh2.visibility==View.GONE){
                    loadNextPageAndProgressVisible()
                }
            }
        }
        binding.rvForLocations.addOnScrollListener(listener)
    }
    fun loadNextPageAndProgressVisible(){
        binding.progressNextPage2.visibility=View.VISIBLE
        viewModel.loadNextPage(viewModel.locations.value!!.info?.next.toString())
    }
    fun pullToRefresh(){
        binding.progressRefresh2.visibility = View.VISIBLE
        viewModel.pullToRefresh()
        adapterLocation.notifyDataSetChanged()
    }
    fun addBottomSheetForFilter(){
        binding.btnFilter2.setOnClickListener {
            CharactersBottomSheetForFilter.show(requireActivity())
        }
    }
    fun followVisibleEndProgressBarLoad(){
        followVisibleEndRefresh()
        followVisibleEndNextLoadPage()
    }
    fun followInformationToast(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.informationToast
                    .onEach {
                        if (it != "default"){
                            Toast.makeText(requireActivity(),it, Toast.LENGTH_LONG).show()
                        }
                    }
                    .collect()
            }
        }
    }
    fun followVisibleEndRefresh(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.stateEndLoadingRefresh
                    .onEach {
                        if (it==true){
                            viewModel.setStateLoadRefreshDisable()
                            binding.progressRefresh2.visibility = View.GONE
                        }
                        adapterLocation.notifyDataSetChanged()
                    }
                    .collect()
            }
        }
    }
    fun setClickCloseFilter(){
        binding.imCloseFilterFind2.setOnClickListener {
            viewModel.resetEnableFilterFind()
            binding.progressRefresh2.visibility = View.VISIBLE
            viewModel.pullToRefresh()
            viewModel.nullableDefaultUrlAfterClosFilter()
            adapterLocation.notifyDataSetChanged()
        }

    }
    fun followFilterEnable(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isFilterEnable
                    .onEach {
                        if (it == true){
                            binding.imCloseFilterFind2.visibility = View.VISIBLE
                        }else{
                            binding.imCloseFilterFind2.visibility = View.GONE

                        }
                        adapterLocation.notifyDataSetChanged()
                    }
                    .collect()
            }
        }
    }
    fun followVisibleEndNextLoadPage(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.stateEndLoadingNextPage
                    .onEach {
                        if (it == true){
                            viewModel.setStateLoadNextEnd()
                            binding.progressNextPage2.visibility = View.GONE
                        }
                        adapterLocation.notifyDataSetChanged()
                    }
                    .collect()
            }
        }
    }

    override fun clickitem(item: LocationDomain) {
        parentFragmentManager.saveFragmentInstanceState(this)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, DetailLocationFragment())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LocationsFragment()
    }

    override fun createBinding(): FragmentLocationsBinding {
        return FragmentLocationsBinding.inflate(layoutInflater)
    }

    override fun initDaggerComponent(function: () -> Unit) {
        LocationsComponent.init(requireActivity()).inject(this)
    }
}