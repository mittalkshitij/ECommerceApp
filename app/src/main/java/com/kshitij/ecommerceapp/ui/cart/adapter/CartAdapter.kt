package com.kshitij.ecommerceapp.ui.cart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kshitij.ecommerceapp.ui.dashboard.model.Items
import com.kshitij.ecommerceapp.databinding.ItemCartBinding

class CartAdapter(var context : Context, private var cartItems: ArrayList<Items>)
    : RecyclerView.Adapter<CartAdapter.MyCartViewHolder>() {

    class MyCartViewHolder(private var binding : ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindingCartViews(cartItems: Items){

            binding.titleTextViewCart.text = cartItems.title
            binding.priceTextViewCart.text = "${cartItems.price}"
            Glide.with(itemView).load(cartItems.image).into(binding.imageViewCart)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartViewHolder {
         val view = ItemCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyCartViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyCartViewHolder, position: Int) {

        holder.bindingCartViews(cartItems[position])
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

}