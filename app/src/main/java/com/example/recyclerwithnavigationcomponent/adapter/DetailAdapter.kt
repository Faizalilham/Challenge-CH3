package com.example.recyclerwithnavigationcomponent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerwithnavigationcomponent.databinding.ListItemBinding
import com.example.recyclerwithnavigationcomponent.model.Detail
import com.example.recyclerwithnavigationcomponent.model.Huruf

class DetailAdapter(val listener : Clicked):RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<Detail>(){
        override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean {
            return when{
                oldItem.name != newItem.name -> {
                    false
                }
                else -> true
            }
        }
    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    fun setData(data : MutableList<Detail>) = differ.submitList(data)

    inner class DetailViewHolder(val binding : ListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
       holder.binding.apply {
           tvHuruf.text = differ.currentList[position].name
           card.setOnClickListener {
               listener.onClicked(differ.currentList[position])
           }
       }
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface Clicked{
        fun onClicked(detail: Detail)
    }

}