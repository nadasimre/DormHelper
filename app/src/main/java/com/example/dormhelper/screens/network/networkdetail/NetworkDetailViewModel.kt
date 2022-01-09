package com.example.dormhelper.screens.network.networkdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dormhelper.model.NetDorm

class NetworkDetailViewModel(dorm: NetDorm, application: Application): AndroidViewModel(application) {

    private val _selectedDorm = MutableLiveData<NetDorm>()
    val selectedDorm: LiveData<NetDorm>
        get() = _selectedDorm

    init {
        _selectedDorm.value = dorm
    }

    val roomCount: String
        get() {
            return _selectedDorm.value?.roomCount.toString()
        }

    val bisNmr: String
        get() {
            if (_selectedDorm.value?.bisNmr.isNullOrBlank()) {
                return "-"
            }else{
                return _selectedDorm.value?.bisNmr!!
            }
        }

    val busNmr: String
        get() {
            if (_selectedDorm.value?.busNmr.isNullOrBlank()) {
                return "-"
            }else{
                return _selectedDorm.value?.busNmr!!
            }
        }
}