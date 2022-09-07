package com.example.recyclerwithnavigationcomponent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerwithnavigationcomponent.adapter.DetailAdapter
import com.example.recyclerwithnavigationcomponent.databinding.FragmentDetailBinding
import com.example.recyclerwithnavigationcomponent.model.Detail


class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private lateinit var detailAdapter: DetailAdapter
//    private lateinit var phoneAdapter: PhoneAdapter

    //    private val apiKeyPhone = "https://api-mobilespecs.azharimm.site/v2/brands" // API MATI PANTEK
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var dataResult : MutableList<Detail>


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
        val id = args.idArray
        val datas = resources.getStringArray(id)
        dataResult = mutableListOf()
        for(i in datas){
            dataResult.add(Detail(i))
        }
        setRecycler(dataResult)
        search(dataResult)

    }


    private fun setRecycler(data : MutableList<Detail>){
        detailAdapter = DetailAdapter(object : DetailAdapter.Clicked{
            override fun onClicked(detail: Detail) {
                val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/search?q=${detail.name}"))
                    startActivity(intent)
            }
        })
        detailAdapter.setData(data)
        binding.detailRecycler.apply{
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun search(result : List<Detail>){
        binding.btnSearch.setOnClickListener {
            val search = binding.etSearch.text.toString().toLowerCase()
            val resultSearch = result.filter { it.name.toLowerCase().contains(search) }
            Log.d("DATAS","$resultSearch")

            if(resultSearch.isNotEmpty()){
                setRecycler(resultSearch.toMutableList())
                binding.imgResult.visibility = View.GONE
            }else{
                setRecycler(resultSearch.toMutableList())
                binding.imgResult.visibility = View.VISIBLE
                binding.imgResult.setImageResource(R.drawable.not_found)
                Toast.makeText(requireActivity(), "404 tidak ada blog", Toast.LENGTH_SHORT).show()

            }
        }
    }



//        val policy = ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)

//        if(isOnline(requireActivity())){
//            setRecycler()
//            binding.imgResult.setImageURI(null)
//            binding.etSearch.visibility = View.VISIBLE
//            binding.btnSearch.visibility = View.VISIBLE
//        }else{
//            Toast.makeText(requireActivity(), "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show()
//            binding.imgResult.setImageResource(R.drawable.not_found)
//            binding.etSearch.visibility = View.GONE
//            binding.btnSearch.visibility = View.GONE
//    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    fun isOnline(context :Context): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val capabilities =
//            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        if (capabilities != null) {
//            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                return true
//            }
//        }
//        return false
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun setRecycler(){
//        abjad = args.abjad
//        val gson = Gson()
//        val getDataPhone = gson.fromJson(URL(apiKeyPhone).readText(),ListPhone::class.java)
//        val result = getDataPhone.data.filter { it.brand_name.toLowerCase().startsWith(abjad.toLowerCase()) }
//        Log.d("LISTDATAAPI","$result")
//        phoneAdapter = PhoneAdapter(
//             object : PhoneAdapter.Clicked{
//                override fun onClicked(phone: Phone) {
//                    val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/search?q=${phone.brand_name}"))
//                    startActivity(intent)
//                }
//            })
//
//        phoneAdapter.setData()
//        binding.detailRecycler.apply{
//            adapter = phoneAdapter
//            layoutManager = LinearLayoutManager(requireActivity())
//        }
//    }


}