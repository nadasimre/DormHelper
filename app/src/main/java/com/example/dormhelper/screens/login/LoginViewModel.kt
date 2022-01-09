package com.example.dormhelper.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dormhelper.database.UserDatabaseDAO
import com.example.dormhelper.model.User
import kotlinx.coroutines.launch

class LoginViewModel(val database: UserDatabaseDAO) : ViewModel() {

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var loginError = MutableLiveData<String?>()
    var registerClicked = MutableLiveData<Boolean>()

    var user = MutableLiveData<User>()

    fun loginClicked() {
        if (username.value.isNullOrBlank() || password.value.isNullOrBlank()) {
            loginError.value = "Username or password is empty"
        } else {
            viewModelScope.launch {
                user.value = database.get(username.value!!, password.value!!)
                if (user.value == null) {
                    loginError.value = "There is no such user"
                } else {
                    loginError.value = null
                }
            }
        }
    }

    fun onCancelClicked() {
        username.value = ""
        password.value = ""
    }

    fun navigateToRegister(){
        registerClicked.value = true
    }

    fun navigateDone(){
        registerClicked.value = false
    }
}