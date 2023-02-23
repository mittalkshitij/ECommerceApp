package com.kshitij.ecommerceapp.ui.login.view

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
import com.kshitij.ecommerceapp.ui.register.view.RegistrationFragment
import com.kshitij.ecommerceapp.databinding.FragmentLoginBinding
import com.kshitij.ecommerceapp.ui.login.viewmodel.LoginViewModel
import com.kshitij.ecommerceapp.ui.login.base.LoginViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = UsersDatabase.getInstance(application).usersDatabaseDao

        val repository = UsersRepository(dao)

        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.myLoginViewModel = loginViewModel

        binding.lifecycleOwner = this



        loginViewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.donetoast()
            }
        })

        loginViewModel.errotoastUsername.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(
                    requireContext(),
                    "User doesnt exist,please Register!",
                    Toast.LENGTH_SHORT
                ).show()
                loginViewModel.donetoastErrorUsername()
            }
        })

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please check your Password", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.donetoastInvalidPassword()
            }
        })

//        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner, Observer { hasFinished->
//            if (hasFinished == true){
//
//                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
//                loginViewModel.doneNavigatingRegiter()
//            }
//        })

        binding.signButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.loginFragmentLogin,
                RegistrationFragment()
            )?.addToBackStack(null)?.commit()
        }

        loginViewModel.navigatetoDashboardActivity.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){

                findNavController().navigate(R.id.action_loginFragment_to_dashboardActivity2)
                loginViewModel.doneNavigatingDashboardActivity()
            }
        })

        return binding.root
    }

}