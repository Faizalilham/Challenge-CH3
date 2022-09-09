package com.example.challengerecycler.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.challengerecycler.model.Huruf

class HurufDiffUtil(
    private val oldList : MutableList<Huruf>,
    private val newList : MutableList<Huruf>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].abjad == newList[newItemPosition].abjad
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].abjad != newList[newItemPosition].abjad -> false
            else -> true
        }
    }
}