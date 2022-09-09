package com.example.challengerecycler.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.challengerecycler.R
import com.example.challengerecycler.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var linearLayout: LinearLayout
    private var getId : Int = 0
    private var getData : String = ""
    private var array : Array<String> = arrayOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentDetailBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).supportActionBar!!.hide()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayout = binding.container
        linearLayout.orientation = LinearLayout.VERTICAL

        getData = arguments?.getChar("data").toString()
        getId = arguments?.getInt("id")!!
        array = resources.getStringArray(getId)
        dinamicButton()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun dinamicButton(){
        for(element in array){
            val button = Button(activity)
            val  param =  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            param.setMargins(10,10,10,10)
            button.layoutParams = param
            button.text = element
            button.id = array.indexOf(element)
            button.background = resources.getDrawable(R.drawable.bg_card_recycler)
            button.setTextColor(Color.BLACK)
            linearLayout.addView(button)

            val btn = button.findViewById<Button>(button.id)

            btn.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=$element"))
                startActivity(intent)
            }

        }

    }


}