package com.example.dormhelper.screens.network.networkdetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dormhelper.model.NetDorm

class NetworkDetailFactory(private val dorm: NetDorm, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NetworkDetailViewModel::class.java)) {
            return NetworkDetailViewModel(dorm, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}