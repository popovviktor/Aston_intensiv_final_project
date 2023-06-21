package com.myapplication.finalproject.app.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.myapplication.finalproject.app.app.App
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val viewModelClass: Class<VM>,
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = requireNotNull(_binding) { "Binding isn't init" }

    private var _viewModel: VM? = null
    protected val viewModel get() = requireNotNull(_viewModel) { "ViewModel isn't init" }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDaggerComponent(){
            (requireActivity().applicationContext as App).charactersComponent()
        }
        _viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = createBinding()
        return binding.root
    }

    abstract fun createBinding(): VB
    abstract fun initDaggerComponent(function: () -> Unit)
}