package com.junino.tourguide.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.junino.tourguide.R
import com.junino.tourguide.databinding.FragmentHomeBinding
import com.junino.tourguide.ui.DetailActivity
import com.junino.tourguide.ui.home.tabs.SectionPagerAdapter
import com.junino.tourguide.ui.login.LoginActivity
import com.junino.tourguide.utils.isLoggedIn
import com.junino.tourguide.utils.saveWisata

class HomeFragment : Fragment() {

    companion object {
        private val TAB_TITLE = arrayOf(
            R.string.rekomendasi,
            R.string.air,
            R.string.gunung,
            R.string.kuliner
        )
    }

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SectionPagerAdapter(requireActivity())
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
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