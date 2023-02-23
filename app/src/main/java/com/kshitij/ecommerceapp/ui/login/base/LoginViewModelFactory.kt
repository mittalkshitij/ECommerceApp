package com.kshitij.ecommerceapp.ui.login.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kshitij.ecommerceapp.data.repository.UsersRepository
import com.kshitij.ecommerceapp.ui.login.viewmodel.LoginViewModel
import java.lang.IllegalArgumentException


class LoginViewModelFactory(
    private  val repository: UsersRepository,
    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}