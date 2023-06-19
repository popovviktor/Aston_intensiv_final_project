package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.bottomsheet.BaseBottomSheet
import com.myapplication.finalproject.databinding.FragmentBottomSheetCharactersBinding

class CharactersBottomSheetForFilter: BaseBottomSheet<FragmentBottomSheetCharactersBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        }else{
            it.isActivated =false
            it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
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