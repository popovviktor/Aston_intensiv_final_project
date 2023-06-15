package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.featureChararcters.presentation.adapter.AdapterForCharacters
import com.myapplication.finalproject.app.core.base.fragment.BaseFragment
import com.myapplication.finalproject.featureChararcters.di.CharactersComponent
import com.myapplication.finalproject.databinding.FragmentCharactersBinding


class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
CharactersViewModel::class.java
){
    override fun createBinding(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }
    private val adapter = AdapterForCharacters()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("ssss")
        val vm = viewModel
        vm.getInfo()
        // vm.getMovie()
        //vm.withoutDAgger()
        vm._live.observe(requireActivity(), Observer {
            println("sssssss")
            vm.saveInDb(vm._live.value!!)
            val rv =view.findViewById<RecyclerView>(R.id.rvForCharacters)
            adapter.list.addAll(it.results!!)
            rv.adapter = adapter
            val view = view.findViewById<ConstraintLayout>(R.id.constr_id)

            val listenerForRv = object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE){
                        println("верхний порог")
                        val progressBar = binding.progressStart
                        progressBar.visibility = View.VISIBLE
                        val progressBar2 = binding.progressEnd
                        progressBar2.visibility=View.GONE
                        vm.loadNewPage(vm._live.value!!.info?.prev.toString())
                    }
                    if (!recyclerView.canScrollVertically(1)&&newState == RecyclerView.SCROLL_STATE_IDLE){
                        println("нижний порог")
                        val progressBar = binding.progressEnd
                        progressBar.visibility=View.VISIBLE
                        val progressBar2 = binding.progressStart
                        progressBar2.visibility = View.GONE
                        vm.loadNewPage(vm._live.value!!.info?.next.toString())
                    }
                }
            }
            vm.isLoading.observe(requireActivity(), Observer {
                if (it == false){
                    binding.progressEnd.visibility = View.GONE
                    binding.progressStart.visibility = View.GONE
                }
            })
            rv.addOnScrollListener(listenerForRv)
            binding.btnFilter.setOnClickListener {
                println("ssss")
                CharactersBottomSheetForFilter.show(requireActivity())
            }
        })
    }

    override fun initDaggerComponent(function: () -> Unit) {
        CharactersComponent.init(requireActivity()).inject(this)
    }

}