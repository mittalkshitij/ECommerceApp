package com.kshitij.ecommerceapp.ui.register.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kshitij.ecommerceapp.data.model.Users
import com.kshitij.ecommerceapp.data.repository.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class RegisterViewModel(private val repository: UsersRepository, application: Application) :
    AndroidViewModel(application), Observable {

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val inputUsername = MutableLiveData<String?>()

    @Bindable
    val inputPassword = MutableLiveData<String?>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _navigateToDashboardActivity = MutableLiveData<Boolean>()

    val navigateToDashboardActivity: LiveData<Boolean>
        get() = _navigateToDashboardActivity

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    fun submitButton() {

        if (inputName.value == null || inputEmail.value == null || inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {

            uiScope.launch {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (usersNames != null) {
                    _errorToastUsername.value = true
                } else {

                    val name = inputName.value
                    val email = inputEmail.value
                    val username = inputUsername.value
                    val password = inputPassword.value
                    insert(Users(0, name.toString(), email.toString(), username.toString(),
                        password.toString()
                    ))

                    inputName.value = null
                    inputEmail.value = null
                    inputUsername.value = null
                    inputPassword.value = null
                    _navigateToDashboardActivity.value = true
                }
            }
        }
    }

    fun doneNavigatingDashboardActivity() {
        _navigateToDashboardActivity.value = false
    }

    fun doneToast() {
        _errorToast.value = false
    }

    fun doneToastUserName() {
        _errorToast.value = false
    }

    private fun insert(user: Users): Job = viewModelScope.launch {
        repository.insert(user)
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}