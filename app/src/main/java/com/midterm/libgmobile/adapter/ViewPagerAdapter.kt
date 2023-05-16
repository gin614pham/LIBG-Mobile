@file:Suppress("DEPRECATION")

package com.midterm.libgmobile.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.midterm.libgmobile.CallCardListFragment
import com.midterm.libgmobile.R

class ViewPagerAdapter(
    private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val titles = arrayOf(
        R.string.pending,
        R.string.unpaid,
        R.string.all
    )

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CallCardListFragment("Pending")
            1 -> CallCardListFragment("Unpaid")
            else -> CallCardListFragment("")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(titles[position])
    }
}
