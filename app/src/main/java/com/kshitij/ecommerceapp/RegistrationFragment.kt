package com.kshitij.ecommerceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kshitij.ecommerceapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    lateinit var binding : FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.loginButtonReg.setOnClickListener {

            fragmentManager?.beginTransaction()?.replace(R.id.mainActivity,LoginFragment())?.addToBackStack(null)?.commit()

        }

        return binding.root
    }



}