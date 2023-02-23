package com.kshitij.ecommerceapp.ui.dashboard.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kshitij.ecommerceapp.R
import com.kshitij.ecommerceapp.databinding.ItemLayoutBinding
import com.kshitij.ecommerceapp.ui.dashboard.model.Items

class ItemsAdapter(var context: Context, private var list : ArrayList<Items>,
                   private val cartListener: CartListener) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>(){

    interface CartListener{
        fun onAdd(cart: Items)
        fun onRemove(cart: Items)
    }

    class MyViewHolder(val context: Context, private var binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindingView(items: Items, cartListener: CartListener) {

            binding.titleTextView.text = items.title
            binding.priceTextView.text = "${items.price}"
            Glide.with(itemView).load(items.image).into(binding.imageView)

            binding.addCartButton.setOnClickListener {

                if(binding.addCartButton.text == "Add to cart"){
                    binding.addCartButton.text =context.getString(R.string.added)
                    binding.addCartButton.setTextColor(Color.RED)
                    cartListener.onAdd(items)
                }else{

                    binding.addCartButton.text =context.getString(R.string.add_to_cart)
                    binding.addCartButton.setTextColor(Color.BLACK)
                    cartListener.onRemove(items)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindingView(list[position], cartListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}