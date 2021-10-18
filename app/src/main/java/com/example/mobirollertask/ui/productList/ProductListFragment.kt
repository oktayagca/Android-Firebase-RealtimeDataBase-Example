package com.example.mobirollertask.ui.productList

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobirollertask.R
import com.example.mobirollertask.databinding.FragmentProductListBinding
import com.example.mobirollertask.models.entity.Product
import com.example.mobirollertask.ui.home.HomeFragmentDirections
import com.example.mobirollertask.utils.hasInternetConnection
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

    override fun onResume() {
        super.onResume()
        getProductList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

    private fun getProductList() {
        if(hasInternetConnection(requireActivity())){
        viewModel.getProducts().observe(viewLifecycleOwner,{
            if(it.isNullOrEmpty()){
                binding.noDataImageView.visibility = View.VISIBLE
            }else{
                setData(it)
                Log.v("firebase",it.toString())
                binding.noDataImageView.visibility = View.GONE
            }

        })
        }
        else{
            val dialog = AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Please check network connection")
                .setPositiveButton("ok") { dialog, _ ->
                    dialog.dismiss()
                }
            dialog.show()
        }
    }

    private fun getProductsSorted(path:String) {
        if(hasInternetConnection(requireActivity())){
            viewModel.getProductsSorted(path).observe(viewLifecycleOwner,{
                if(it.isNullOrEmpty()){
                    binding.noDataImageView.visibility = View.VISIBLE
                }else{
                    setData(it)
                    Log.v("firebase",it.toString())
                    binding.noDataImageView.visibility = View.GONE
                }

            })
        }
        else{
            val dialog = AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Please check network connection")
                .setPositiveButton("ok") { dialog, _ ->
                    dialog.dismiss()
                }
            dialog.show()
        }
    }


    private fun setData(restaurantList: List<Product>) {
        adapter.setProductList(restaurantList,this)
    }

    private fun initViews() {
        val options = resources.getStringArray(R.array.sortingOptions)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, options)
        binding.apply {
            autoCompleteTextView.setAdapter(arrayAdapter)
            autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
                when(position){
                    0->getProductsSorted("uploadDate")
                    1->getProductsSorted("price")
                }
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(context,2)
        }
    }


    override fun onClick(product: Product) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment2(product)
        findNavController().navigate(action)
    }
}