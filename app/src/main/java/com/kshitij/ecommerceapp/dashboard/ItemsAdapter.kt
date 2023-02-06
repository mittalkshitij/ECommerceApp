package com.kshitij.ecommerceapp.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.CalendarContract.Colors
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.color.MaterialColors
import com.kshitij.ecommerceapp.R
import com.kshitij.ecommerceapp.cart.CartItemsAdapter
import com.kshitij.ecommerceapp.databinding.ItemLayoutBinding



class ItemsAdapter(var list : ArrayList<Items>) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>()  {

    class MyViewHolder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        lateinit var cartList : ArrayList<Items>


        fun bindingView(items: Items){

            binding.titleTextView.setText(items.title)
            binding.priceTextView.setText("Price : $"+items.price.toString())
            Glide.with(itemView).
            load(items.image).into(binding.imageView)

            cartList= ArrayList()

            binding.addCartButton.setOnClickListener {

                if(binding.addCartButton.text=="Add to cart") {

                    binding.addCartButton.setText("Added")
                    cartList.add(items)
                    Log.i("itemscart",cartList.toString())
                    CartItemsAdapter(cartList)
                    binding.addCartButton.setTextColor(Color.RED)
                }else{
                    binding.addCartButton.text = "Add to cart"
                    binding.addCartButton.setTextColor(itemView.context.resources.getColor(R.color.teal_200))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.addCartButton.setOnClickListener {

            holder.binding.addCartButton.setText("Added")
            holder.cartList.add(list[position])
            Log.i("itemscart1",holder.cartList.toString())

        }

        holder.bindingView(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

}