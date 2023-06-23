package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResult
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.bottomsheet.BaseBottomSheet
import com.myapplication.finalproject.databinding.FragmentBottomSheetCharactersBinding
private var filterStatus:String? = null
private var filterGender:String? = null
private const val REQUEST_KEY = "request_key_find_filter"
private const val FILTER_NAME = "filter_name"
private const val FILTER_STATUS = "filter_status"
private const val FILTER_SPECIES = "filter_species"
private const val FILTER_TYPE = "filter_type"
private const val FILTER_GENDER = "filter_gender"

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
            setFragmentResultWithFilterForFound()
        }
    }
    fun getBundleWithFilterParams():Bundle{
        val bundle = Bundle()
        if (getFilterName()!=null){
            bundle.putString(FILTER_NAME,getFilterName())
        }
        if (getFilterStatus()!=null){
            bundle.putString(FILTER_STATUS,getFilterStatus())
        }
        if (getFilterSpecies()!=null){
            bundle.putString(FILTER_SPECIES,getFilterSpecies())
        }
        if (getFilterType()!=null){
            bundle.putString(FILTER_TYPE,getFilterType())
        }
        if (getFilterGender()!=null){
            bundle.putString(FILTER_GENDER,getFilterGender())
        }
        return bundle
    }
    fun setFragmentResultWithFilterForFound(){
        val bundle = getBundleWithFilterParams()
        setFragmentResult(
            REQUEST_KEY,
            bundle
        )
        onDismiss(requireDialog())
    }
    fun getFilterStatus():String?{
        return filterStatus
    }
    fun getFilterGender():String?{
        return filterGender
    }
    fun getFilterName():String?{
        val findName = binding.editFilterName.text.toString()
        if (findName.length>0){
            return findName
        }else{
            return null
        }
    }
    fun getFilterSpecies():String?{
        val findSpecies = binding.editSpeciesFilter.text.toString()
        if (findSpecies.length>0){
            return findSpecies
        }else{return null}
    }
    fun getFilterType():String?{
        val findType = binding.editTypeFilter.text.toString()
        if (findType.length>0){
            return findType
        }else{return null}
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