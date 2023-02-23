package com.kshitij.ecommerceapp.ui.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kshitij.ecommerceapp.R
import com.kshitij.ecommerceapp.data.UsersDatabase
import com.kshitij.ecommerceapp.data.repository.UsersRepository
import com.kshitij.ecommerceapp.databinding.FragmentRegistrationBinding
import com.kshitij.ecommerceapp.ui.login.view.LoginFragment
import com.kshitij.ecommerceapp.ui.register.base.RegisterViewModelFactory
import com.kshitij.ecommerceapp.ui.register.viewmodel.RegisterViewModel

class RegistrationFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentRegistrationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_registration, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = UsersDatabase.getInstance(application).usersDatabaseDao

        val repository = UsersRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.myRegViewModel = registerViewModel

        binding.lifecycleOwner = this

        registerViewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoast()
            }
        })

        registerViewModel.errotoastUsername.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoastUserName()
            }
        })

        registerViewModel.navigatetoDashboardActivity.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){

                findNavController().navigate(R.id.action_loginFragment_to_dashboardActivity2)
                registerViewModel.doneNavigatingDashboardActivity()
            }
        })

        binding.loginButtonReg.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.registrationFragmentReg,
                LoginFragment()
            )?.addToBackStack(null)?.commit()
        }

        return binding.root
    }



}