package com.example.challengerecycler.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengerecycler.adapter.HurufAdapter
import com.example.challengerecycler.communicator.Communicator
import com.example.challengerecycler.databinding.FragmentHomeBinding
import com.example.challengerecycler.databinding.SortItemBinding
import com.example.challengerecycler.model.Huruf
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var sortItemBinding: SortItemBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var hurufAdapter: HurufAdapter
    private lateinit var communicator: Communicator
    private lateinit var listData : MutableList<Huruf>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        communicator = activity as Communicator
        (activity as AppCompatActivity).supportActionBar!!.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        setFilterData()
    }

    // Set Recycler
    private fun setRecycler(){

        listData = mutableListOf()
        for(i in 'A'..'Z'){
            listData.add(Huruf(i))
        }

        hurufAdapter = HurufAdapter(listData,object : HurufAdapter.Clicked{
            override fun onClick(huruf: Huruf) {
                // R.array.position // Int
                val a  = resources.getIdentifier(huruf.abjad.toString(),"array", (activity as AppCompatActivity).packageName)
                communicator.setData(a,huruf.abjad)
            }
        })

        binding.recycler.apply{
            adapter = hurufAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    // Set Fitur Sort
    private fun setFilterData(){
        binding.fab.setOnClickListener {
            sortItemBinding = SortItemBinding.inflate(layoutInflater)
            bottomSheetDialog = BottomSheetDialog(requireActivity())
            bottomSheetDialog.setContentView(sortItemBinding.root)
            bottomSheetDialog.show()

            sortItemBinding.rbAZ.setOnCheckedChangeListener { _, isChecked : Boolean ->
                if(isChecked){
                    val sorted = listData.sortedBy { it.abjad.toString() }
                    hurufAdapter.setData(sorted.toMutableList())
                }
            }
            sortItemBinding.rbZA.setOnCheckedChangeListener { _, isChecked : Boolean ->
                if(isChecked){
                    val sorted = listData.sortedByDescending { it.abjad.toString() }
                    hurufAdapter.setData(sorted.toMutableList())
                }
            }
        }
    }

}