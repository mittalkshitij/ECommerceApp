package com.kshitij.ecommerceapp.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.kshitij.ecommerceapp.R
import com.kshitij.ecommerceapp.dashboard.DashboardActivity
import com.kshitij.ecommerceapp.dashboard.Items
import com.kshitij.ecommerceapp.profile.ProfileActivity

class CartActivity : AppCompatActivity() {

    lateinit var cartRecyclerView: RecyclerView
    lateinit var cartItemsList : ArrayList<Items>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartRecyclerView = findViewById(R.id.cartRecyclerView)

        cartRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        cartItemsList = ArrayList<Items>()

        cartRecyclerView.adapter =CartItemsAdapter(cartItemsList)

        Log.i("itemcart",cartItemsList.toString())

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        bottomNavigationView.selectedItemId = R.id.cart

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboard -> {
                    startActivity(Intent(applicationContext, DashboardActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.cart -> return@setOnItemSelectedListener true
                R.id.profile -> {
                    startActivity(Intent(applicationContext, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }
}