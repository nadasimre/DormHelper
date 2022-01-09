package com.example.dormhelper.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dormhelper.database.UserDatabaseDAO
import com.example.dormhelper.model.User

class MenuViewFactory(private val user: User, private val dataSource: UserDatabaseDAO,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(user, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}