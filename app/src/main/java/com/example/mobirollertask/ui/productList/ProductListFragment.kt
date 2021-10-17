package com.example.mobirollertask.ui.productList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobirollertask.databinding.FragmentProductListBinding
import com.example.mobirollertask.models.entity.Product
import com.example.mobirollertask.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment :Fragment(),IOnClick{
    private var _binding : FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private var adapter: ProductRecyclerViewAdapter = ProductRecyclerViewAdapter()
    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        initViews()
        getProductList()
        return binding.root
    }

    private fun getProductList() {
        viewModel.getProducts().observe(viewLifecycleOwner,{
            if(it.isNullOrEmpty()){
                binding.noDataImageView.visibility = View.VISIBLE
            }else{
                setData(it!!)
                Log.v("firebase",it.toString())
                binding.noDataImageView.visibility = View.GONE
            }

        })
    }

    private fun setData(restaurantList: List<Product>) {
        adapter.setProductList(restaurantList,this)
    }

    private fun initViews() {
        binding.apply {
            dropdownMenuButton.setOnClickListener{
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(context,2)
        }
    }

    override fun onResume() {
        super.onResume()
        getProductList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

    override fun onClick(product: Product) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment2(product)
        findNavController().navigate(action)
    }

}