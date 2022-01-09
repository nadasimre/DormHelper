package com.example.dormhelper.screens.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dormhelper.database.UserDatabaseDAO
import com.example.dormhelper.model.User
import kotlinx.coroutines.launch
import kotlin.random.Random

class RegistrationViewModel(val database: UserDatabaseDAO) : ViewModel(){

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var email = MutableLiveData<String>()

    var registerError = MutableLiveData<String?>()

    var cancelClicked = MutableLiveData<Boolean>()



     fun registerClicked(){
         viewModelScope.launch{
             if (username.value.isNullOrBlank() || password.value.isNullOrBlank() || email.value.isNullOrBlank()) {
                 registerError.value = "Username, password or email is empty"
             }else {
                 val users = database.getAllUsers()
                 for (item in users) {
                     if(item.email == email.value || item.userName == username.value){
                         registerError.value = "User already exists"
                     }else{
                         registerError.value = null
                     }
                 }
             }
             if(registerError.value.isNullOrBlank()){
                 val user = User(
                     createId(Random.nextInt(0, 100)),
                     username.value.toString(),
                     password.value.toString(),
                     email.value.toString()
                 )

                 database.insert(user)
                 registerError.value = "Successful registration"
             }
         }
    }

    private fun createId(num: Int): Int {
        return num.hashCode() * Random.nextInt(0, 100)
    }

    fun navigateToLogin(){
        cancelClicked.value = true
    }

    fun navigateLoginDone(){
        cancelClicked.value = false
    }
}