package com.myapplication.finalproject.featureEpisodes.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResult
import com.myapplication.finalproject.app.core.base.bottomsheet.BaseBottomSheet
import com.myapplication.finalproject.databinding.FragmentEpisodesBottomSheetForFilterBinding

private const val REQUEST_KEY_EPISODE = "request_key_find_filter_episodes"
private const val FILTER_NAME_EPISODE = "filter_name_episode"
private const val FILTER_EPISODE_EPISODE = "filter_type_episode"

class EpisodesBottomSheetForFilterFragment : BaseBottomSheet<FragmentEpisodesBottomSheetForFilterBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListenerFindButton()
    }
    fun setClickListenerFindButton(){
        binding.btnFindWithFilterEpisode.setOnClickListener {
            setFragmentResultWithFilterForFound()
            parentFragmentManager.popBackStack()
        }
    }
    fun setFragmentResultWithFilterForFound(){
        val bundle = getBundleWithFilterParams()
        setFragmentResult(
            REQUEST_KEY_EPISODE,
            bundle
        )
        onDismiss(requireDialog())
    }
    fun getBundleWithFilterParams():Bundle{
        val bundle = Bundle()
        if (getFilterName()!=null){
            bundle.putString(FILTER_NAME_EPISODE,getFilterName())
        }
        if (getFilterEpisode()!=null){
            bundle.putString(FILTER_EPISODE_EPISODE,getFilterEpisode())
        }
        return bundle
    }
    fun getFilterName():String?{
        val findName = binding.editFilterNameEpisode.text.toString()
        if (findName.length>0){
            return findName
        }else{
            return null
        }
    }
    fun getFilterEpisode():String?{
        val findEpisode = binding.editFilterEpisodeEpisode.text.toString()
        if (findEpisode.length>0){
            return findEpisode
        }else{return null}
    }
    companion object {
        fun show(activity: FragmentActivity) {
            val fragment = EpisodesBottomSheetForFilterFragment()
            fragment.show(
                activity.supportFragmentManager,
                EpisodesBottomSheetForFilterFragment::class.simpleName
            )
        }
    }

    override fun createBinding(): FragmentEpisodesBottomSheetForFilterBinding {
        return FragmentEpisodesBottomSheetForFilterBinding.inflate(layoutInflater)
    }
}