package com.example.dormhelper.screens.dormpresetbuilder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dormhelper.database.UserDatabaseDAO

class DormPresetFactory(private var presetId: Int, private val dataSource: UserDatabaseDAO
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DormPresetViewModel::class.java)) {
            return DormPresetViewModel(presetId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}