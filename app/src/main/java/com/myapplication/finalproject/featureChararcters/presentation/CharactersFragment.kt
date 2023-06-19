package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.featureChararcters.presentation.adapter.AdapterForCharacters
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.databinding.FragmentCharactersBinding

import com.myapplication.finalproject.featureChararcters.di.CharactersComponent
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain


private val adapter = AdapterForCharacters()
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
CharactersViewModel::class.java
){
    override fun createBinding(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("this text once")
        if (savedInstanceState==null){
            println("savedIntance")
            getLoadDefaultPageAndFollowResult()
        }else{
            println("get def load page")
            defaultSettingForRecyclerCharacters()
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
        binding.rvForCharacters.layoutManager = GridLayoutManager(activity,2, RecyclerView.VERTICAL,false)
        binding.rvForCharacters.adapter = adapter
        addBottomSheetForFilter()
        followVisibleEndProgressBarLoad()
        setListenerForRecyclerCharacters()
    }
    fun setListInAdapter(characters: ArrayList<CharacterDomain>){
        adapter.list= characters
    }
    fun setListenerForRecyclerCharacters(){
        val listener = object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE){
                    println("верхний порог")
                    binding.progressStart.visibility = View.VISIBLE
                    binding.progressEnd.visibility=View.GONE
                    viewModel.loadNewPage(viewModel._live.value!!.info?.prev.toString())
                }
                if (!recyclerView.canScrollVertically(1)&&newState == RecyclerView.SCROLL_STATE_IDLE){
                    println("нижний порог")
                    binding.progressEnd.visibility=View.VISIBLE
                    val progressBar2 = binding.progressStart
                    progressBar2.visibility = View.GONE
                    viewModel.loadNewPage(viewModel._live.value!!.info?.next.toString())
                }
            }
        }
        binding.rvForCharacters.addOnScrollListener(listener)
    }
    fun addBottomSheetForFilter(){
        binding.btnFilter.setOnClickListener {
            CharactersBottomSheetForFilter.show(requireActivity())
        }
    }
    fun followVisibleEndProgressBarLoad(){
        activity?.let { it1 ->
            viewModel._isLoading.observe(it1, Observer {
                println("change load")
                if (it.IsLoadingPrevPage == false||it.IsLoadingNewPage ==false){
                    binding.progressEnd.visibility = View.GONE
                    binding.progressStart.visibility = View.GONE
                    println(viewModel._live.value!!.results?.size)
                    adapter.notifyDataSetChanged()
                }
            })
        }
    }

}