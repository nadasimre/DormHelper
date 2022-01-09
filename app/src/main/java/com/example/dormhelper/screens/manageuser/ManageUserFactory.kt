package com.example.dormhelper.screens.manageuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dormhelper.database.UserDatabaseDAO
import com.example.dormhelper.model.User

class ManageUserFactory(private val user: User, private val dataSource: UserDatabaseDAO): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManageUserViewModel::class.java)) {
            return ManageUserViewModel(user, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}