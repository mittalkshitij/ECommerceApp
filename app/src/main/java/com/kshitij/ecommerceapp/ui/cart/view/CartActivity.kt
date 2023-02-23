package com.kshitij.ecommerceapp.ui.cart.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kshitij.ecommerceapp.R
import com.kshitij.ecommerceapp.ui.dashboard.view.DashboardActivity
import com.kshitij.ecommerceapp.ui.dashboard.model.Items
import com.kshitij.ecommerceapp.ui.cart.adapter.CartAdapter
import com.kshitij.ecommerceapp.ui.profile.view.ProfileActivity

class CartActivity : AppCompatActivity(){

    private var recyclerViewCart : RecyclerView?=null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val checkoutButton = findViewById<Button>(R.id.checkoutButton)
        val list  = intent?.getParcelableArrayListExtra("cartItems", Items::class.java)

        recyclerViewCart = findViewById(R.id.cartRecyclerView)

        recyclerViewCart?.apply {
            layoutManager =
                LinearLayoutManager(this@CartActivity, RecyclerView.VERTICAL, false)
            adapter = CartAdapter(applicationContext, list!!)
        }

        Log.d("ItemList", list.toString())

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

        checkoutButton.setOnClickListener {

                val builder = AlertDialog.Builder(this)
                    .setTitle("Checkout")
                    .setMessage("Do you want to confirm your purchases?")
                    .setCancelable(false)

                    .setPositiveButton("Yes") { dialogInterface, _ ->

                        Toast.makeText(this, "Purchase Completed", Toast.LENGTH_LONG).show()
                        dialogInterface.dismiss()
                        this.finish()
                        list?.clear()
                    }
                    .setNegativeButton("No") { dialogInterface, _ ->

                        dialogInterface.dismiss()
                    }
            val ad = builder.create()
            ad.show()
            }
        }
}
