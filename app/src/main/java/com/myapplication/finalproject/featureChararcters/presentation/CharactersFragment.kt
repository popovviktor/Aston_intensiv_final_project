package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.featureChararcters.presentation.adapter.AdapterForCharacters
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentCharactersBinding

import com.myapplication.finalproject.featureChararcters.di.CharactersComponent
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val LANDSCAPE_ORIENTATION = 2
private val adapter = AdapterForCharacters()
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
CharactersViewModel::class.java
){
    override fun createBinding(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerFragmentResultListener()
        if (savedInstanceState==null){
            getLoadDefaultPageAndFollowResult()
        }else{
            defaultSettingForRecyclerCharacters()
        }
    }
    fun registerFragmentResultListener(){
        setFragmentResultListener("request_key") { key, bundle ->
            val selectedSort = bundle.getString("extra_key")
            println(selectedSort)
            // применение полученной сортировки
        }
    }

    override fun initDaggerComponent(function: () -> Unit) {
        CharactersComponent.init(requireActivity()).inject(this)
    }
    fun getLoadDefaultPageAndFollowResult(){
        viewModel.getInfo()
        viewModel._live.observe(requireActivity(), Observer {
            if (it!=null){
                setListInAdapter(it.results!!)
                defaultSettingForRecyclerCharacters()
            }
        })
    }
    fun defaultSettingForRecyclerCharacters(){
        setLayoutManagerInRecycler()
        binding.rvForCharacters.adapter = adapter
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
        binding.rvForCharacters.layoutManager = GridLayoutManager(activity,
            spanCountForGridLayout, RecyclerView.VERTICAL,false)
    }
    fun setListInAdapter(characters: ArrayList<CharacterDomain>){
        adapter.list= characters
    }
    fun setScrollListenerForRecyclerCharacters(){
        val listener = object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE&&
                    binding.progressNext.visibility==View.GONE && binding.progressPrev.visibility==View.GONE){
                    loadPrevPageAndProgressVisible()
                }
                if (!recyclerView.canScrollVertically(1)&&newState == RecyclerView.SCROLL_STATE_IDLE&&
                    binding.progressPrev.visibility==View.GONE&&binding.progressNext.visibility==View.GONE){
                    loadNextPageAndProgressVisible()
                }
            }
        }
        binding.rvForCharacters.addOnScrollListener(listener)
    }
    fun loadNextPageAndProgressVisible(){
        binding.progressPrev.visibility=View.VISIBLE
        viewModel.loadNextPage(viewModel._live.value!!.info?.next.toString())
    }
    fun loadPrevPageAndProgressVisible(){
        binding.progressNext.visibility = View.VISIBLE
        viewModel.loadPrevPage(viewModel._live.value!!.info?.prev.toString())
    }
    fun addBottomSheetForFilter(){
        binding.btnFilter.setOnClickListener {
            CharactersBottomSheetForFilter.show(requireActivity())
        }
    }
    fun followVisibleEndProgressBarLoad(){
        followVisibleEndPrevLoadPage()
        followVisibleEndNextLoadPage()
    }
    fun followVisibleEndPrevLoadPage(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.stateLoadingPrev
                    .onEach {
                        if (it.IsLoading==true){
                            viewModel.setStateLoadPrevEnd()
                            if (it.SizeLoadItem!=null&& it.SizeLoadItem!!>0){
                                adapter.notifyItemRangeInserted(0,it.SizeLoadItem!!)
                            }
                            binding.progressNext.visibility = View.GONE
                        }
                    }
                    .collect()
            }
        }
    }
    fun followVisibleEndNextLoadPage(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.stateLoadingNext
                    .onEach {
                        if (it == true){
                            viewModel.setStateLoadNextEnd()
                            binding.progressPrev.visibility = View.GONE
                            adapter.notifyDataSetChanged()
                        }
                    }
                    .collect()
            }
        }
    }

}