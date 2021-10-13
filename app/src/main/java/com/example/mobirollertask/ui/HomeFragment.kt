package com.example.mobirollertask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobirollertask.databinding.FragmentHomeBinding


class HomeFragment: Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        val adapter = HomeViewPagerAdapter(requireActivity())
        binding.apply {
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false
            bottomNavigation.setOnItemSelectedListener{item->
                when (item.title) {
                    "Products" -> viewPager.currentItem = 0
                    "Product Add" -> viewPager.currentItem = 1
                }
                true
            }
        }
    }

}