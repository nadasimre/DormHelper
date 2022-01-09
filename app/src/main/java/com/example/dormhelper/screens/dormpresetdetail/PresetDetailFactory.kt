package com.example.dormhelper.screens.dormpresetdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dormhelper.database.UserDatabaseDAO
import com.example.dormhelper.model.DormPreset

class PresetDetailFactory(private var preset: DormPreset, private val dataSource: UserDatabaseDAO,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PresetDetailViewModel::class.java)) {
            return PresetDetailViewModel(preset, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}