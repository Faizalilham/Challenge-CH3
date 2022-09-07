package com.example.recyclerwithnavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerwithnavigationcomponent.adapter.HurufAdapter
import com.example.recyclerwithnavigationcomponent.databinding.FragmentHomeBinding
import com.example.recyclerwithnavigationcomponent.model.Huruf


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var hurufAdapter: HurufAdapter
    private lateinit var listData : MutableList<Huruf>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
    }

    private fun setRecycler() {

        listData = mutableListOf()
        for(i in 'A'..'Z'){
            listData.add(Huruf(i))
        }

        hurufAdapter = HurufAdapter(object : HurufAdapter.Clicked{
            override fun onClick(huruf: Huruf) {
                val id = resources.getIdentifier(huruf.abjad.toString(),"array", (activity as AppCompatActivity).packageName)
                val move = HomeFragmentDirections.actionHomeFragmentToDetailFragment(huruf.abjad.toString(),id)
                findNavController().navigate(move)
            }
        })

        hurufAdapter.setData(listData)
        binding.recycler.apply{
            adapter = hurufAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }


}