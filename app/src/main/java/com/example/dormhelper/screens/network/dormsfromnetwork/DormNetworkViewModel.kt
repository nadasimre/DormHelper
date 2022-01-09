package com.example.dormhelper.screens.network.dormsfromnetwork

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dormhelper.model.NetDorm
import kotlinx.coroutines.launch

enum class DormApiStatus { LOADING, ERROR, DONE }

class DormNetworkViewModel : ViewModel() {

    private val _properties = MutableLiveData<List<NetDorm>>()
    val properties: LiveData<List<NetDorm>>
        get() = _properties

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response


    private val _status = MutableLiveData<DormApiStatus>()
    val status: LiveData<DormApiStatus>
        get() = _status

    private val _navigateToSelectedDorm = MutableLiveData<NetDorm?>()
    val navigateToSelectedDorm: LiveData<NetDorm?>
        get() = _navigateToSelectedDorm

    init {
        getDormProperties()
    }

    private fun getDormProperties() {
        viewModelScope.launch {
            _status.value = DormApiStatus.LOADING
            try {
                val listResult = DormApi.retrofitService.getProperties()
                _response.value = "There are currently ${listResult.size} dorms in Kortrijk."
                _status.value = DormApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = DormApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun displayDormDetails(dorm: NetDorm) {
        _navigateToSelectedDorm.value = dorm
    }

    fun displayDormDetailsComplete() {
        _navigateToSelectedDorm.value = null
    }
}