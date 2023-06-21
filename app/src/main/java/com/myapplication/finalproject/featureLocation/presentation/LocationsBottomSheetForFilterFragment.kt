package com.myapplication.finalproject.featureLocation.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResult
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.core.base.bottomsheet.BaseBottomSheet
import com.myapplication.finalproject.databinding.FragmentLocationsBottomSheetForFilterBinding

private const val REQUEST_KEY_LOCATION = "request_key_find_filter_location"
private const val FILTER_NAME_LOCATION = "filter_name_location"
private const val FILTER_TYPE_LOCATION = "filter_type_location"
private const val FILTER_DIMENSION_LOCATION = "filter_dimension_location"

class LocationsBottomSheetForFilterFragment : BaseBottomSheet<FragmentLocationsBottomSheetForFilterBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListenerFindButton()
    }
    fun setClickListenerFindButton(){
        binding.btnFindWithFilterLocation.setOnClickListener {
            setFragmentResultWithFilterForFound()
        }
    }
    fun setFragmentResultWithFilterForFound(){
        val bundle = getBundleWithFilterParams()
        setFragmentResult(
            REQUEST_KEY_LOCATION,
            bundle
        )
        onDismiss(requireDialog())
    }
    fun getBundleWithFilterParams():Bundle{
        val bundle = Bundle()
        if (getFilterName()!=null){
            bundle.putString(FILTER_NAME_LOCATION,getFilterName())
        }
        if (getTypeFilter()!=null){
            bundle.putString(FILTER_TYPE_LOCATION,getTypeFilter())
        }
        if (getDimesionFilter()!=null){
            bundle.putString(FILTER_DIMENSION_LOCATION,getDimesionFilter())
        }
        return bundle
    }
    fun getFilterName():String?{
        val findName = binding.editFilterNameLocation.text.toString()
        if (findName.length>0){
            return findName
        }else{
            return null
        }
    }
    fun getTypeFilter():String?{
        val findSpecies = binding.editFilterTypeLocation.text.toString()
        if (findSpecies.length>0){
            return findSpecies
        }else{return null}
    }
    fun getDimesionFilter():String?{
        val findType = binding.editFilterDimensionLocation.text.toString()
        if (findType.length>0){
            return findType
        }else{return null}
    }



    companion object {
        fun show(activity: FragmentActivity) {
            val fragment = LocationsBottomSheetForFilterFragment()
            fragment.show(
                activity.supportFragmentManager,
                LocationsBottomSheetForFilterFragment::class.simpleName
            )
        }
    }

    override fun createBinding(): FragmentLocationsBottomSheetForFilterBinding {
        return FragmentLocationsBottomSheetForFilterBinding.inflate(layoutInflater)
    }
}