package com.myapplication.finalproject.featureEpisodes.presentation

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
import com.myapplication.finalproject.databinding.FragmentEpisodesBinding
import com.myapplication.finalproject.featureChararcters.presentation.DetailCharacterFragment
import com.myapplication.finalproject.featureEpisodes.di.EpisodesComponent
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.presentation.adapter.AdapterForEpisodes
import com.myapplication.finalproject.featureEpisodes.presentation.adapter.onClickItemEpisodeListener
import com.myapplication.finalproject.featureEpisodes.presentation.models.ParamsFilterEpisodesForDBFind
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.presentation.*
import com.myapplication.finalproject.featureLocation.presentation.adapter.SpaceItemDecorationLocations
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val REQUEST_KEY_EPISODE = "request_key_find_filter_episodes"
private const val FILTER_NAME_EPISODE = "filter_name_episode"
private const val FILTER_EPISODE_EPISODE = "filter_type_episode"
private const val LANDSCAPE_ORIENTATION = 2


class EpisodesFragment : BaseFragment<FragmentEpisodesBinding,EpisodesViewModel>(
    EpisodesViewModel::class.java
),onClickItemEpisodeListener {
    private val adapterEpisode = AdapterForEpisodes()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerFragmentResultListener()
        followCharactersUpdateForUi()
        if (savedInstanceState==null){
            println("nullllll")
            if (adapterEpisode.list.size<1){
                println("over adapter load")
                getLoadDefaultPageAndFollowCharacterUpdateForUi()
            }
        }
        setClickCloseFilter()
        followFilterEnable()
        followInformationToast()
    }
    fun followCharactersUpdateForUi(){
        defaultSettingForRecyclerCharacters()
        viewModel.episodes.observe(requireActivity(), Observer {
            if (it!=null){
                adapterEpisode.list= it.results!!
            }
        })
    }
    fun registerFragmentResultListener(){
        setFragmentResultListener(REQUEST_KEY_EPISODE) { key, bundle ->
            val filterName = bundle.getString(FILTER_NAME_EPISODE)
            val filterEpisode = bundle.getString(FILTER_EPISODE_EPISODE)
            binding.progressRefreshEpisodes.visibility = View.VISIBLE
            viewModel.enableFilterFind()
            val params = ParamsFilterEpisodesForDBFind(filterName,filterEpisode)
            viewModel.getDefaultUrlForFindWithFilter(params)
        }
    }


    fun getLoadDefaultPageAndFollowCharacterUpdateForUi(){
        followCharactersUpdateForUi()
        viewModel.getDefaultPage()

    }
    fun defaultSettingForRecyclerCharacters(){
        setLayoutManagerInRecycler()
        binding.rvForEpisodes.adapter = adapterEpisode
        adapterEpisode.onClickListener = this
        addSetClickListenerOpenBottomSheetForFilter()
        followVisibleEndProgressBarLoad()
        setScrollListenerForRecyclerCharacters()
        initAddItemDecorations()
    }
    fun initAddItemDecorations(){
        binding.rvForEpisodes.addItemDecoration(SpaceItemDecorationLocations(5))

    }
    fun setLayoutManagerInRecycler(){
        val orientation = requireActivity().resources.configuration.orientation
        var spanCountForGridLayout = 2
        if (orientation== LANDSCAPE_ORIENTATION){
            spanCountForGridLayout = 3
        }
        binding.rvForEpisodes.layoutManager = GridLayoutManager(activity,
            spanCountForGridLayout, RecyclerView.VERTICAL,false)
    }
    fun setScrollListenerForRecyclerCharacters(){
        val listener = object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE&&
                    binding.progressRefreshEpisodes.visibility==View.GONE && binding.progressNextPageEpisode.visibility==View.GONE){
                    pullToRefresh()
                }
                if (!recyclerView.canScrollVertically(1)&&newState == RecyclerView.SCROLL_STATE_IDLE&&
                    binding.progressNextPageEpisode.visibility==View.GONE&&binding.progressRefreshEpisodes.visibility==View.GONE&&
                        viewModel.episodes.value!=null){
                    loadNextPageAndProgressVisible()
                }
            }
        }
        binding.rvForEpisodes.addOnScrollListener(listener)
    }
    fun loadNextPageAndProgressVisible(){
        binding.progressNextPageEpisode.visibility=View.VISIBLE
        viewModel.loadNextPage(viewModel.episodes.value!!.info?.next.toString())
    }
    fun pullToRefresh(){
        binding.progressRefreshEpisodes.visibility = View.VISIBLE
        viewModel.pullToRefresh()
        adapterEpisode.notifyDataSetChanged()
    }
    fun addSetClickListenerOpenBottomSheetForFilter(){
        binding.btnFilterEpisodes.setOnClickListener {
            EpisodesBottomSheetForFilterFragment.show(requireActivity())
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
                            binding.progressRefreshEpisodes.visibility = View.GONE
                        }
                        adapterEpisode.notifyDataSetChanged()
                    }
                    .collect()
            }
        }
    }
    fun setClickCloseFilter(){
        binding.imCloseFilterFindEpisode.setOnClickListener {
            viewModel.resetEnableFilterFind()
            binding.progressRefreshEpisodes.visibility = View.VISIBLE
            viewModel.pullToRefresh()
            viewModel.nullableDefaultUrlAfterClosFilter()
            adapterEpisode.notifyDataSetChanged()
        }

    }
    fun followFilterEnable(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isFilterEnable
                    .onEach {
                        if (it == true){
                            binding.imCloseFilterFindEpisode.visibility = View.VISIBLE
                        }else{
                            binding.imCloseFilterFindEpisode.visibility = View.GONE

                        }
                        adapterEpisode.notifyDataSetChanged()
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
                            binding.progressNextPageEpisode.visibility = View.GONE
                        }
                        adapterEpisode.notifyDataSetChanged()
                    }
                    .collect()
            }
        }
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



    companion object {
        @JvmStatic
        fun newInstance() = EpisodesFragment()
    }
    override fun createBinding(): FragmentEpisodesBinding {
        return FragmentEpisodesBinding.inflate(layoutInflater)
    }

    override fun initDaggerComponent(function: () -> Unit) {
        EpisodesComponent.init(requireActivity()).inject(this)
    }

}