package com.kshitij.ecommerceapp.ui.dashboard.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kshitij.ecommerceapp.databinding.ItemLayoutBinding
import com.kshitij.ecommerceapp.ui.dashboard.model.Items

class ItemsAdapter(var context: Context, var list : ArrayList<Items>, val cartListener: CartListener) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>()  {

    interface CartListener{
        fun onAdd(cart: Items)
        fun onRemove(cart: Items)
    }

    class MyViewHolder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

//        lateinit var cartItems :ArrayList<Items>

        fun bindingView(items: Items, cartListener: CartListener) {

            binding.titleTextView.setText(items.title)
            binding.priceTextView.setText("Price : $" + items.price.toString())
            Glide.with(itemView).load(items.image).into(binding.imageView)

//            cartItems = ArrayList<Items>()

            binding.addCartButton.setOnClickListener {

                if(binding.addCartButton.text == "Add to cart"){
                    binding.addCartButton.text ="Added"
                    binding.addCartButton.setTextColor(Color.RED)
                    cartListener.onAdd(items)
                    //adding to cart functionality
//                    cartItems.add(items)

                }else{

                    binding.addCartButton.text ="Add to cart"
                    binding.addCartButton.setTextColor(Color.BLACK)
                    cartListener.onRemove(items)
                    //remove from cart functionality
//                    cartItems.remove(items)

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindingView(list[position], cartListener)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}