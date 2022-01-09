package com.example.dormhelper.screens.manageuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dormhelper.database.UserDatabaseDAO
import com.example.dormhelper.model.User
import kotlinx.coroutines.launch

class ManageUserViewModel(private var loggedInUser: User, val database: UserDatabaseDAO) : ViewModel() {

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var email = MutableLiveData<String>()

    var error = MutableLiveData<String>()

    private var _onDeleteUser = MutableLiveData<Boolean>()
    val onDeleteUser: LiveData<Boolean>
        get() = _onDeleteUser

    fun userNameChange(){
        if(username.value.isNullOrBlank()) {
            error.value="username field is empty"
        }else{
            viewModelScope.launch {
                val dorms = database.getUsersDorms(loggedInUser.userName)
                for(item in dorms){
                    item.nameOfUser = username.value!!
                    database.updateDorm(item)
                }

                loggedInUser.userName = username.value!!
                error.value = "successful username change"
                database.update(loggedInUser)
            }
        }
    }

    fun passwordChange(){
        if(password.value.isNullOrBlank()) {
            error.value="password field is empty"
        }else{
            viewModelScope.launch {
                loggedInUser.password = password.value!!
                error.value = "successful password change"
                database.update(loggedInUser)
            }
        }
    }

    fun emailChange(){
        if(email.value.isNullOrBlank()) {
            error.value="email field is empty"
        }else{
            viewModelScope.launch {
                loggedInUser.email = email.value!!
                error.value = "successful email change"
                database.update(loggedInUser)
            }
        }
    }

    fun onDelete(){
        viewModelScope.launch {
            for(item in database.getUsersDorms(loggedInUser.userName)){
                database.deleteDorm(item.id)
            }
            database.deleteUser(loggedInUser.id)

            _onDeleteUser.value = true
        }
    }

    fun afterDeleteNavigated(){
        _onDeleteUser.value = false
    }
}