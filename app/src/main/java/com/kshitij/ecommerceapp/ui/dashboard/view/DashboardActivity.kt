package com.kshitij.ecommerceapp.ui.dashboard.view



import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kshitij.ecommerceapp.R
import com.kshitij.ecommerceapp.dashboard.RetrofitClient
import com.kshitij.ecommerceapp.ui.cart.view.CartActivity
import com.kshitij.ecommerceapp.ui.dashboard.model.Items
import com.kshitij.ecommerceapp.ui.dashboard.adapter.ItemsAdapter
import com.kshitij.ecommerceapp.ui.profile.view.ProfileActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var itemsList: ArrayList<Items>
    lateinit var cartList: ArrayList<Items>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        itemsList = ArrayList<Items>()
        cartList =  ArrayList()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        bottomNavigationView.selectedItemId = R.id.dashboard

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.cart -> {
                    startActivity(Intent(applicationContext, CartActivity::class.java).apply {
                        putExtra("cartItems", cartList)
                    })
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.dashboard -> return@setOnItemSelectedListener true
                R.id.profile -> {
                    startActivity(Intent(applicationContext, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }


        GlobalScope.launch {

            Log.i("mytag", Thread.currentThread().name.toString())
            val myCall = RetrofitClient.myRetrofit.getItems()

            myCall.enqueue(object : Callback<List<Items>> {
                override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {

                    itemsList = response.body() as ArrayList<Items>

                    recyclerView.adapter = ItemsAdapter(applicationContext,itemsList, object: ItemsAdapter.CartListener {
                        override fun onAdd(cart: Items) {
                            cartList.add(cart)
                        }

                        override fun onRemove(cart: Items) {
                            cartList.remove(cart)
                        }

                    })

                }

                override fun onFailure(call: Call<List<Items>>, t: Throwable) {

                    Log.i("myTag", "$t")
                }

            })
        }

    }


}

