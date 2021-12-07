package com.junino.tourguide.ui.home.tabs.gunung

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.junino.tourguide.R
import com.junino.tourguide.databinding.FragmentGunungBinding
import com.junino.tourguide.databinding.FragmentKulinerBinding
import com.junino.tourguide.enums.TagEnum
import com.junino.tourguide.models.Wisata
import com.junino.tourguide.ui.DetailActivity
import com.junino.tourguide.ui.home.HomeViewModel
import com.junino.tourguide.ui.home.WisataRowAdapter
import com.junino.tourguide.ui.login.LoginActivity
import com.junino.tourguide.utils.isLoggedIn

class GunungFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentGunungBinding? = null

    private lateinit var adapter : WisataRowAdapter
    private var listWisata = ArrayList<Wisata>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentGunungBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvGunung.layoutManager = linearLayoutManager

        adapter = WisataRowAdapter(requireContext(),::onListItemClick)

        showData()
    }

    private fun showData(){
        val db = Firebase.firestore
        val listWisata = ArrayList<Wisata>()
        val wisata = Wisata()

        db.collection("wisata").get()
            .addOnSuccessListener { snapshot ->
                for (data in snapshot){

                    val wisataData = data.data

                    wisata.nama = wisataData["nama"].toString()
                    wisata.alamat = wisataData["alamat"].toString()
                    wisata.deskripsi = wisataData["deskripsi"].toString()
                    wisata.jenis = wisataData["jenis"].toString()
                    wisata.latitude = wisataData["latitude"].toString().toDouble()
                    wisata.longitude = wisataData["longitude"].toString().toDouble()
                    wisata.tags = wisataData["tags"] as MutableList<TagEnum>

                    Log.d("GunungFragment", "Data : $wisata")
                    listWisata.add(wisata)
                    Log.d("GunungFragment", "listData : $listWisata")
                }
            }


        if (listWisata.isEmpty()){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            adapter.setDataWisata(listWisata)
            binding.rvGunung.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        if (!isLoggedIn){
            startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

    fun onListItemClick(position: Int){
        val intent = Intent(
            activity,
            DetailActivity::class.java,
        )
        intent.putExtra("model", homeViewModel.testData[position])
        activity?.startActivity(intent)
    }

}