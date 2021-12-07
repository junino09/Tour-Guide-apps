package com.junino.tourguide.ui.home.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.junino.tourguide.ui.home.tabs.gunung.GunungFragment
import com.junino.tourguide.ui.home.tabs.kuliner.KulinerFragment
import com.junino.tourguide.ui.home.tabs.pantai.PantaiFragment
import com.junino.tourguide.ui.home.tabs.rekomendasi.RekomendasiFragment

class SectionPagerAdapter(fm : FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> RekomendasiFragment()
            1 -> PantaiFragment()
            2 -> GunungFragment()
            3 -> KulinerFragment()
            else -> Fragment()
        }
    }

}