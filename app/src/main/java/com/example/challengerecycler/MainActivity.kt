package com.example.challengerecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.challengerecycler.communicator.Communicator
import com.example.challengerecycler.databinding.ActivityMainBinding
import com.example.challengerecycler.fragment.DetailFragment
import com.example.challengerecycler.fragment.HomeFragment

class MainActivity : AppCompatActivity(),Communicator {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HomeFragment())
    }

    private fun setFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }

    override fun setData(id:Int,data: Char) {
        val bundle = Bundle()
        bundle.putInt("id",id)
        bundle.putChar("data",data)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.container,detailFragment)
            .addToBackStack(null).commit()
    }

}