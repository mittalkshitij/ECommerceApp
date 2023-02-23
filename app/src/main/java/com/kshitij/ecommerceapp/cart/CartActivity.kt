package com.kshitij.ecommerceapp.cart

import android.content.ClipData.Item
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kshitij.ecommerceapp.R
import com.kshitij.ecommerceapp.dashboard.DashboardActivity
import com.kshitij.ecommerceapp.dashboard.Items
import com.kshitij.ecommerceapp.profile.ProfileActivity

class CartActivity : AppCompatActivity(){

    lateinit var recyclerViewCart: RecyclerView
    //lateinit var cartList: ArrayList<Items>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        var checkoutButton = findViewById<Button>(R.id.checkoutButton)
        val list  = intent?.getParcelableArrayListExtra("cartItems", Items::class.java)

        recyclerViewCart = findViewById<RecyclerView>(R.id.cartRecyclerView)
        recyclerViewCart.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        recyclerViewCart.adapter = CartAdapter(applicationContext,list!!)

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

                var builder = AlertDialog.Builder(this)
                    .setTitle("Checkout")
                    .setMessage("Do you want to confirm your purchases?")
                    .setCancelable(false)

                    .setPositiveButton("Yes", DialogInterface.OnClickListener {
                            dialogInterface, i ->
                        Toast.makeText(this,"Purchase Completed",Toast.LENGTH_LONG).show()

                        dialogInterface.dismiss()
                        this.finish()
                        list.clear()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialogInterface, i ->

                            dialogInterface.dismiss()

                    })


                var ad = builder.create()
                ad.show()
            }
        }

}
