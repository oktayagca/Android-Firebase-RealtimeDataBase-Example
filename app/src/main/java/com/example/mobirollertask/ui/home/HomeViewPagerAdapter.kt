package com.example.mobirollertask.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mobirollertask.ui.productAdd.ProductAddFragment
import com.example.mobirollertask.ui.productList.ProductListFragment


private const val FRAGMENT_COUNT = 2

class HomeViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int =FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ProductListFragment()
            1 -> ProductAddFragment()
            else -> ProductListFragment()
        }
    }
}