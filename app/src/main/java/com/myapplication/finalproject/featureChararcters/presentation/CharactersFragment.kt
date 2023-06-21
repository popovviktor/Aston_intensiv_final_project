package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.featureChararcters.presentation.adapter.AdapterForCharacters
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentCharactersBinding
import com.myapplication.finalproject.featureChararcters.di.CharactersComponent
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.presentation.adapter.SpaceItemDecorationCharacters
import com.myapplication.finalproject.featureChararcters.presentation.adapter.onClickItemCharacterListener
import com.myapplication.finalproject.featureLocation.presentation.adapter.SpaceItemDecorationLocations
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val REQUEST_KEY = "request_key_find_filter"
private const val FILTER_NAME = "filter_name"
private const val FILTER_STATUS = "filter_status"
private const val FILTER_SPECIES = "filter_species"
private const val FILTER_TYPE = "filter_type"
private const val FILTER_GENDER = "filter_gender"
private const val LANDSCAPE_ORIENTATION = 2


class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
CharactersViewModel::class.java
),onClickItemCharacterListener{
    private val adapter = AdapterForCharacters()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerFragmentResultListener()
        followCharactersUpdateForUi()
        if (savedInstanceState==null){
            if (adapter.list.size<1){
                getLoadDefaultPageAndFollowCharacterUpdateForUi()
            }
        }
        setClickCloseFilter()
        followFilterEnable()
        followInformationToast()
    }
    fun followCharactersUpdateForUi(){
        defaultSettingForRecyclerCharacters()
        viewModel.characters.observe(requireActivity(), Observer {
            if (it!=null){
                adapter.list= it.results!!
            }
        })
    }
    fun registerFragmentResultListener(){
        setFragmentResultListener(REQUEST_KEY) { key, bundle ->
            val filterName = bundle.getString(FILTER_NAME)
            val filterStatus = bundle.getString(FILTER_STATUS)
            val filterSpecies = bundle.getString(FILTER_SPECIES)
            val filterType = bundle.getString(FILTER_TYPE)
            val filterGender = bundle.getString(FILTER_GENDER)
            binding.progressRefresh.visibility = View.VISIBLE
            viewModel.enableFilterFind()
            viewModel.getDefaultUrlForFindWithFilter(filterName,filterStatus,
            filterSpecies,filterType,filterGender)
        }
    }


    fun getLoadDefaultPageAndFollowCharacterUpdateForUi(){
        followCharactersUpdateForUi()
        viewModel.getDefaultPage()

    }
    fun defaultSettingForRecyclerCharacters(){
        setLayoutManagerInRecycler()
        binding.rvForCharacters.adapter = adapter
        adapter.onClickListener  =this
        addBottomSheetForFilter()
        followVisibleEndProgressBarLoad()
        setScrollListenerForRecyclerCharacters()
        initAddItemDecorations()
    }
    fun initAddItemDecorations(){
        binding.rvForCharacters.addItemDecoration(SpaceItemDecorationCharacters(5))

    }
    fun setLayoutManagerInRecycler(){
        val orientation = requireActivity().resources.configuration.orientation
        var spanCountForGridLayout = 2
        if (orientation== LANDSCAPE_ORIENTATION){
            spanCountForGridLayout = 3
        }
        binding.rvForCharacters.layoutManager = GridLayoutManager(activity,
            spanCountForGridLayout, RecyclerView.VERTICAL,false)
    }
    fun setScrollListenerForRecyclerCharacters(){
        val listener = object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE&&
                    binding.progressRefresh.visibility==View.GONE && binding.progressNextPage.visibility==View.GONE){
                    pullToRefresh()
                }
                if (!recyclerView.canScrollVertically(1)&&newState == RecyclerView.SCROLL_STATE_IDLE&&
                    binding.progressNextPage.visibility==View.GONE&&binding.progressRefresh.visibility==View.GONE){
                    loadNextPageAndProgressVisible()
                }
            }
        }
        binding.rvForCharacters.addOnScrollListener(listener)
    }
    fun loadNextPageAndProgressVisible(){
        binding.progressNextPage.visibility=View.VISIBLE
        if (viewModel.characters.value!=null){
            viewModel.loadNextPage(viewModel.characters.value!!.info?.next.toString())
        }

    }
    fun pullToRefresh(){
        binding.progressRefresh.visibility = View.VISIBLE
        viewModel.pullToRefresh()
        adapter.notifyDataSetChanged()
    }
    fun addBottomSheetForFilter(){
        binding.btnFilter.setOnClickListener {
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
                            Toast.makeText(requireActivity(),it,Toast.LENGTH_LONG).show()
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
                            binding.progressRefresh.visibility = View.GONE
                        }
                        adapter.notifyDataSetChanged()
                    }
                    .collect()
            }
        }
    }
    fun setClickCloseFilter(){
        binding.imCloseFilterFind.setOnClickListener {
            viewModel.resetEnableFilterFind()
            binding.progressRefresh.visibility = View.VISIBLE
            viewModel.pullToRefresh()
            viewModel.nullableDefaultUrlAfterClosFilter()
            adapter.notifyDataSetChanged()
        }

    }
    fun followFilterEnable(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isFilterEnable
                    .onEach {
                        if (it == true){
                            binding.imCloseFilterFind.visibility = View.VISIBLE
                        }else{
                            binding.imCloseFilterFind.visibility = View.GONE

                        }
                        adapter.notifyDataSetChanged()
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
                            binding.progressNextPage.visibility = View.GONE
                        }
                        adapter.notifyDataSetChanged()
                    }
                    .collect()
            }
        }
    }

    override fun clickitem(item: CharacterDomain) {
        parentFragmentManager.saveFragmentInstanceState(this)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,DetailCharacterFragment())
            .addToBackStack(null)
            .commit()
    }
    companion object {
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }
    override fun initDaggerComponent(function: () -> Unit) {
        CharactersComponent.init(requireActivity()).inject(this)
    }
    override fun createBinding(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }


}