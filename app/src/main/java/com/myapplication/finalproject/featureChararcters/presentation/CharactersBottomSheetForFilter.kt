package com.myapplication.finalproject.featureChararcters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.myapplication.finalproject.app.core.base.bottomsheet.BaseBottomSheet
import com.myapplication.finalproject.databinding.FragmentBottomSheetCharactersBinding

class CharactersBottomSheetForFilter: BaseBottomSheet<FragmentBottomSheetCharactersBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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