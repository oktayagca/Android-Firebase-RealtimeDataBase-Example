package com.example.mobirollertask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobirollertask.databinding.ActivityMainBinding
import com.example.mobirollertask.databinding.FragmentProductAddBinding

class ProductAddFragment:Fragment() {
    private var _binding : FragmentProductAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductAddBinding.inflate(inflater,container,false)
        return binding.root
    }
}