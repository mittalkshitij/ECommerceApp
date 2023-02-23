package com.kshitij.ecommerceapp.ui.register.viewmodel

import android.app.Application
import android.util.Log
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

    init {
        Log.i("MYTAG", "init")
    }

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

    private var _navigatetoDashboardActivity = MutableLiveData<Boolean>()

    val navigatetoDashboardActivity: LiveData<Boolean>
        get() = _navigatetoDashboardActivity

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    fun sumbitButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (inputName.value == null || inputEmail.value == null || inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
//            withContext(Dispatchers.IO) {
                val usersNames = repository.getUserName(inputUsername.value!!)
                Log.i("MYTAG", usersNames.toString() + "------------------")
                if (usersNames != null) {
                    _errorToastUsername.value = true
                    Log.i("MYTAG", "Inside if Not null")
                } else {

                    val name = inputName.value!!
                    val email = inputEmail.value!!
                    val username = inputUsername.value!!
                    val password = inputPassword.value!!
                    Log.i("MYTAG", "insidi Sumbit")
                    insert(Users(0, name, email, username, password))
                    inputName.value = null
                    inputEmail.value = null
                    inputUsername.value = null
                    inputPassword.value = null
                    _navigatetoDashboardActivity.value = true
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigatingDashboardActivity() {
        _navigatetoDashboardActivity.value = false
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastUserName() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting  username")
    }

    private fun insert(user: Users): Job = viewModelScope.launch {
        repository.insert(user)
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}