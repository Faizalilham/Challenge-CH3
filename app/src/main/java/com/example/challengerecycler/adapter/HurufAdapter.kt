package com.example.challengerecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.challengerecycler.R
import com.example.challengerecycler.databinding.ListItemBinding
import com.example.challengerecycler.model.Huruf

class HurufAdapter(data : MutableList<Huruf>,val listener : Clicked):RecyclerView.Adapter<HurufAdapter.HurufViewHolder>() {

    private var oldData = data

    inner class HurufViewHolder(val binding : ListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HurufViewHolder {
        return HurufViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HurufViewHolder, position: Int) {
        holder.binding.apply {
            tvHuruf.text = oldData[position].abjad.toString()
            card.setOnClickListener {
                listener.onClick(oldData[position])
            }
            card.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim))
        }
    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    interface Clicked{
        fun onClick(huruf: Huruf)
    }

    fun setData(newData : MutableList<Huruf>){
        val diffUtil = HurufDiffUtil(oldData,newData)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldData = newData
        diffResult.dispatchUpdatesTo(this)
    }

}