package com.kshitij.ecommerceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kshitij.ecommerceapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false);


        binding.signButton.setOnClickListener {

            fragmentManager?.beginTransaction()?.replace(R.id.mainActivity,RegistrationFragment())?.addToBackStack(null)?.commit()
        }

        return binding.root;
    }

}