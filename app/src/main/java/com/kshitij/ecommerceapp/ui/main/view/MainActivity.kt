package com.kshitij.ecommerceapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kshitij.ecommerceapp.R
import com.kshitij.ecommerceapp.databinding.ActivityMainBinding

import io.paperdb.Paper

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding?.mainActivity?.let {
            supportFragmentManager.beginTransaction().replace(it.id, MainFragment::class.java, null)
                .addToBackStack(null).commit()
        }
    }
}