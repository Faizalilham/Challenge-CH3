package com.example.recyclerwithnavigationcomponent.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerwithnavigationcomponent.databinding.ListItemBinding
import com.example.recyclerwithnavigationcomponent.model.Huruf

class HurufAdapter(val listener : Clicked):RecyclerView.Adapter<HurufAdapter.HurufViewHolder>() {

    private var diffCallBack = object : DiffUtil.ItemCallback<Huruf>(){
        override fun areItemsTheSame(oldItem: Huruf, newItem: Huruf): Boolean {
            return oldItem.abjad == newItem.abjad
        }

        override fun areContentsTheSame(oldItem: Huruf, newItem: Huruf): Boolean {
            return when{
                oldItem.abjad != newItem.abjad -> {
                    false
                }
                else -> true
            }
        }
    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    fun setData(data : MutableList<Huruf>) = differ.submitList(data)

    inner class HurufViewHolder(val binding : ListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HurufViewHolder {
        return HurufViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HurufViewHolder, position: Int) {
        holder.binding.apply {
            tvHuruf.text = differ.currentList[position].abjad.toString()
            card.setOnClickListener {
                listener.onClick(differ.currentList[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface Clicked{
        fun onClick(huruf: Huruf)
    }



}