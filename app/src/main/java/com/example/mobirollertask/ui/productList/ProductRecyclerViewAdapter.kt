package com.example.mobirollertask.ui.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobirollertask.databinding.ItemProductListBinding
import com.example.mobirollertask.models.entity.Product

class ProductRecyclerViewAdapter:RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    private lateinit var binding: ItemProductListBinding
    private lateinit var  onClickListener : IOnClick
    private  var products:List<Product> = mutableListOf()

    class ProductViewHolder(private val binding: ItemProductListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Product,onClickListener: IOnClick){
            binding.apply {
                textViewCategory.text = item.category
                textViewTitle.text = item.title
                textViewPrice.text = item.price
                Glide.with(imageView.context)
                    .load(item.imageUri).into(imageView)
                layout.setOnClickListener{
                    onClickListener.onClick(item)
                }
            }
        }
    }

    fun setProductList(products:List<Product>,onClickListener: IOnClick){
        this.onClickListener = onClickListener
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = ItemProductListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item,onClickListener)
    }

    override fun getItemCount(): Int = products.size
}