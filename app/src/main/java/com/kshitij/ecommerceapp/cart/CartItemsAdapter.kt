package com.kshitij.ecommerceapp.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kshitij.ecommerceapp.dashboard.Items
import com.kshitij.ecommerceapp.databinding.ItemLayoutBinding

class CartItemsAdapter(var cartList : ArrayList<Items>) : RecyclerView.Adapter<CartItemsAdapter.MyCartViewHolder>()  {

    class MyCartViewHolder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindingView(items: Items){

            binding.titleTextView.setText(items.title)
            binding.priceTextView.setText("Price : $"+items.price.toString())
            Glide.with(itemView).
            load(items.image).into(binding.imageView)

            Log.i("itemscart2","hi")

            //binding.addCartButton.visibility
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartViewHolder {
        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCartViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyCartViewHolder, position: Int) {

        Log.i("itemscart2",cartList.toString())
        holder.bindingView(cartList[position])

    }

    override fun getItemCount(): Int {

        return cartList.size
    }
}

