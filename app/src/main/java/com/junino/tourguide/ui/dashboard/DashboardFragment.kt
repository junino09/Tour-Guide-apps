package com.junino.tourguide.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.junino.tourguide.R
import com.junino.tourguide.databinding.FragmentDashboardBinding
import com.junino.tourguide.enums.TagEnum
import com.junino.tourguide.models.Wisata
import com.junino.tourguide.ui.DetailActivity
import com.junino.tourguide.ui.home.HomeViewModel
import com.junino.tourguide.ui.home.WisataRowAdapter
import com.junino.tourguide.utils.user

class DashboardFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvFavorit.layoutManager  = linearLayoutManager

        val adapter = WisataRowAdapter(requireContext(),::onListItemClick)
        adapter.setDataWisata(homeViewModel.testData as ArrayList<Wisata>)


        if (homeViewModel.testData.isEmpty()){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvFavorit.adapter = adapter
        }

//        showFavorite()

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    private fun showFavorite(){
        var db = Firebase.firestore

        db.collection("users").get()
            .addOnSuccessListener { snapshot ->
                val nama = ArrayList<String>()

                for (data in snapshot) {
                    nama.add(data.data["favorites"].toString())
                    Log.d("DashboardFragment", "favorite : ${data.data}")
                }
            }
    }

    fun getTags(favs: MutableList<String>): MutableList<TagEnum>{
        val a = mutableSetOf<TagEnum>()
        favs.forEach{
            val w = homeViewModel.testData.first { w -> w.nama!!.replace(" ", "") == it }
            a.addAll(w?.tags!!)
        }

        return a.toMutableList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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