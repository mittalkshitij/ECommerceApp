package com.kshitij.ecommerceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kshitij.ecommerceapp.databinding.FragmentMainBinding
import com.kshitij.ecommerceapp.login.LoginFragment
import com.kshitij.ecommerceapp.register.RegistrationFragment

//import com.kshitij.ecommerceapp.login.LoginFragment
//import com.kshitij.ecommerceapp.register.RegistrationFragment

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        binding.createButton.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainActivity, RegistrationFragment())?.addToBackStack(null)?.commit()
        }

        binding.loginButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainActivity, LoginFragment())
                ?.addToBackStack(null)?.commit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}