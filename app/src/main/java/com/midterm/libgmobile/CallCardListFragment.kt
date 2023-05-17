package com.midterm.libgmobile

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midterm.libgmobile.adapter.RvCallCard
import com.midterm.libgmobile.adapter.RvCallCardIF
import com.midterm.libgmobile.service.CallCardService


class CallCardListFragment(private val status: String) : Fragment(R.layout.fragment_call_card_list) {
    private val callCardService: CallCardService = CallCardService()
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvCallCardAdapter: RvCallCard


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview_list_call_card)
        setAllCallCard(status)
    }

    private fun setAllCallCard(status: String) {
        callCardService.getAllCallCard(status) {list->
            rvCallCardAdapter = RvCallCard(list, object : RvCallCardIF {
                override fun onClick(position: Int) {
                    val bundle = Bundle()
                    bundle.putSerializable("callCard", list[position])
                    val fragment = CallCardDetailFragment()
                    fragment.arguments = bundle
                    requireActivity().supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.slide_in_1,
                            R.anim.slide_out_1,
                            R.anim.slide_in_3,
                            R.anim.slide_out_3
                        )
                        .replace(R.id.frame_layout, fragment)
                        .addToBackStack(null)
                        .commit()
                }

            })
            recyclerView.adapter = rvCallCardAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}
