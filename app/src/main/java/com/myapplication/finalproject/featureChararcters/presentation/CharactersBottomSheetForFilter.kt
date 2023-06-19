package com.myapplication.finalproject.featureChararcters.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResult
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.bottomsheet.BaseBottomSheet
import com.myapplication.finalproject.databinding.FragmentBottomSheetCharactersBinding
private var filterStatus:String? = null
private var filterGender:String? = null
class CharactersBottomSheetForFilter: BaseBottomSheet<FragmentBottomSheetCharactersBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultValuesForFilter()
        setClickListenersForStatusButtons()
        setClickListenersForGenderButtons()
        setClickListenerFindButton()
    }
    fun setClickListenerFindButton(){
        binding.btnFindWithFilter.setOnClickListener {
            setFragmentResult()
        }
    }
    fun setFragmentResult(){
        val bundle = Bundle()
        bundle.putString("extra_key","ssssssss")
        setFragmentResult(
            "request_key",
            bundle
        )
        onDismiss(requireDialog())
    }

    fun defSettingForListenerButtonGender(it: View,btn_disable1:Button,btn_disable2: Button,btn_disable3: Button){
        if (it.isActivated!=true){
            it.isActivated = true
            it.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.teal_700))
            btn_disable1.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            btn_disable2.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            btn_disable3.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            btn_disable1.isActivated = false
            btn_disable2.isActivated = false
            btn_disable3.isActivated = false
            val it_btn = it as Button
            filterGender = it_btn.text.toString()
        }else{
            it.isActivated =false
            it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            filterGender = null
        }
        println(filterGender)
    }
    fun setClickListenersForGenderButtons(){
        setClickListenerGenderMale()
        setClickListenerGenderGenderless()
        setClickListenerGenderFemale()
        setClickListenerGenderUnkown()
    }
    fun setClickListenerGenderUnkown(){
        binding.btnUnknownGender.setOnClickListener {
            defSettingForListenerButtonGender(it,binding.btnMale,
                binding.btnGenderless,binding.btnFemale)
        }
    }
    fun setClickListenerGenderGenderless(){
        binding.btnGenderless.setOnClickListener {
            defSettingForListenerButtonGender(it,binding.btnMale,
                binding.btnFemale,binding.btnUnknownGender)
        }
    }
    fun setClickListenerGenderMale(){
        binding.btnMale.setOnClickListener {
            defSettingForListenerButtonGender(it,binding.btnFemale,
                binding.btnGenderless,binding.btnUnknownGender)
        }
    }
    fun setClickListenerGenderFemale(){
        binding.btnFemale.setOnClickListener {
            defSettingForListenerButtonGender(it,binding.btnMale,
            binding.btnGenderless,binding.btnUnknownGender)
        }
    }

    fun defaultValuesForFilter(){
        filterStatus = null
        filterGender = null
    }
    fun setClickListenersForStatusButtons(){
        setClickListenerStatusAlive()
        setClickListenerStatusDead()
        setClickListenerStatusUnknown()
    }
    fun setClickListenerStatusAlive(){
        binding.btnAliveStatus.setOnClickListener {
            defSettingForListenerButtonStatus(it,binding.btnUnknownStatus,binding.btnDeadStatus)
        }
    }
    fun setClickListenerStatusDead(){
        binding.btnDeadStatus.setOnClickListener {
            defSettingForListenerButtonStatus(it,binding.btnAliveStatus,binding.btnUnknownStatus)
        }
    }
    fun setClickListenerStatusUnknown(){
        binding.btnUnknownStatus.setOnClickListener {
            defSettingForListenerButtonStatus(it,binding.btnAliveStatus,binding.btnDeadStatus)
        }
    }
    fun defSettingForListenerButtonStatus(it: View,btn_disable1:Button,btn_disable2: Button){
        if (it.isActivated!=true){
            it.isActivated = true
            it.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.teal_700))
            btn_disable1.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            btn_disable2.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            btn_disable1.isActivated = false
            btn_disable2.isActivated = false
            val it_btn = it as Button
            filterStatus = it_btn.text.toString()
        }else{
            it.isActivated =false
            it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            filterStatus = null
        }
    }
    companion object {

        fun show(activity: FragmentActivity) {
            val fragment = CharactersBottomSheetForFilter()
            fragment.show(
                activity.supportFragmentManager,
                CharactersBottomSheetForFilter::class.simpleName
            )
        }
    }

    override fun createBinding(): FragmentBottomSheetCharactersBinding {
        return FragmentBottomSheetCharactersBinding.inflate(layoutInflater)
    }
}