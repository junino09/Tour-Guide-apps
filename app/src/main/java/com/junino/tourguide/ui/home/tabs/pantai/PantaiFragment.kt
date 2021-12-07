package com.junino.tourguide.ui.home.tabs.pantai

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.junino.tourguide.R
import com.junino.tourguide.databinding.FragmentGunungBinding
import com.junino.tourguide.databinding.FragmentPantaiBinding
import com.junino.tourguide.enums.TagEnum
import com.junino.tourguide.models.Wisata
import com.junino.tourguide.ui.DetailActivity
import com.junino.tourguide.ui.home.HomeViewModel
import com.junino.tourguide.ui.home.WisataRowAdapter
import com.junino.tourguide.ui.login.LoginActivity
import com.junino.tourguide.utils.isLoggedIn

class PantaiFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentPantaiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentPantaiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvPantai.layoutManager = linearLayoutManager

        val adapter = WisataRowAdapter(requireContext(),::onListItemClick)
        adapter.setDataWisata(homeViewModel.testData as ArrayList<Wisata>)


        if (homeViewModel.testData.isEmpty()){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvPantai.adapter = adapter
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