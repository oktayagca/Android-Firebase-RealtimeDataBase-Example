package com.example.mobirollertask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.mobirollertask.MainActivity
import com.example.mobirollertask.R
import com.example.mobirollertask.databinding.FragmentProductListBinding

class ProductListFragment :Fragment(){
    private var _binding : FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.apply {
            dropdownMenuButton.setOnClickListener{
            }
        }
    }

}