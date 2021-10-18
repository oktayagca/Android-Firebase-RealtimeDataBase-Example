package com.example.mobirollertask.ui.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mobirollertask.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment:Fragment() {
    private var _binding:FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel:ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        initViews()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        viewModel.getProduct().observe(viewLifecycleOwner,{
            binding.apply {
                Glide.with(imageView.context)
                    .load(it.imageUri).into(imageView)
                textViewTitle.text = it.title
                textViewPrice.text = it.price
                textViewDate.text = it.uploadDate
                textViewDesc.text = it.description
            }
        })
    }

}