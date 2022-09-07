package com.example.recyclerwithnavigationcomponent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerwithnavigationcomponent.databinding.ListItemBinding
import com.example.recyclerwithnavigationcomponent.model.Huruf
import com.example.recyclerwithnavigationcomponent.model.Phone

class PhoneAdapter(val listener : Clicked):RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {

    inner class PhoneViewHolder(val binding : ListItemBinding):RecyclerView.ViewHolder(binding.root)

    private var diffCallBack = object : DiffUtil.ItemCallback<Phone>(){
        override fun areItemsTheSame(oldItem: Phone, newItem: Phone): Boolean {
            return oldItem.brand_name == newItem.brand_name
        }

        override fun areContentsTheSame(oldItem: Phone, newItem: Phone): Boolean {
            return when{
                oldItem.brand_name != newItem.brand_name -> {
                    false
                }
                else -> true
            }
        }
    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    fun setData(data : MutableList<Phone>) = differ.submitList(data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
       return PhoneViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
       holder.binding.apply {
           tvHuruf.text = differ.currentList[position].brand_name

           card.setOnClickListener {
               listener.onClicked(differ.currentList[position])
           }
       }

    }
    override fun getItemCount(): Int = differ.currentList.size


    interface Clicked{
        fun onClicked(phone : Phone)
    }
}