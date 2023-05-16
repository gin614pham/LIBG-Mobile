package com.midterm.libgmobile

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.midterm.libgmobile.adapter.ViewPagerAdapter

class CallCardFragment : Fragment(R.layout.fragment_call_card) {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.view_pager)
        val adapter = ViewPagerAdapter(requireContext(), childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_bookmark_add_24)
        tabLayout.getTabAt(1)?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_bookmark_24)
        tabLayout.getTabAt(2)?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_bookmark_added_24)

    }
}