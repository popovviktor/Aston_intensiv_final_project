package com.myapplication.finalproject.featureChararcters.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.app.dip2px

class SpaceItemDecorationCharacters(
    private val vertical_space:Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val space_in_dp = view.context.dip2px(vertical_space).toInt()
        outRect.bottom = space_in_dp
        outRect.top = space_in_dp
        outRect.left = space_in_dp
        outRect.right = space_in_dp
    }

}