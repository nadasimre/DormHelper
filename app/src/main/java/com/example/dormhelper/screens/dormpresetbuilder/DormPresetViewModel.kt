package com.example.dormhelper.screens.dormpresetbuilder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dormhelper.database.UserDatabaseDAO
import kotlinx.coroutines.launch

class DormPresetViewModel(private var id: Int, val database: UserDatabaseDAO) : ViewModel() {

    var presetName = MutableLiveData<String>()
    var size = MutableLiveData<String>()
    var roomCount = MutableLiveData<String>()
    var hasBathroom = MutableLiveData<Boolean>()
    var hasSink = MutableLiveData<Boolean>()
    var hasKitchen = MutableLiveData<Boolean>()
    var isFurnished = MutableLiveData<Boolean>()

    var error = MutableLiveData<String>()

    private val _navigateToMenu = MutableLiveData<Boolean?>()
    val navigateToMenu
        get() = _navigateToMenu

    init{
        hasBathroom.value = false
        hasSink.value = false
        hasKitchen.value = false
        isFurnished.value = false
    }

    fun cancelClicked(){
        size.value = ""
        roomCount.value = ""
        hasBathroom.value = false
        hasSink.value = false
        hasKitchen.value = false
        isFurnished.value = false
        presetName.value = ""
    }

    fun onCreateClicked(){
        if (size.value.isNullOrBlank() || roomCount.value.isNullOrBlank() || presetName.value.isNullOrBlank()) {
            error.value = "Size, rooms or name is empty"
        }else{
            error.value = ""
            viewModelScope.launch {
                val createdPreset = database.getDorm(id)

                createdPreset.size = size.value!!.toInt()
                createdPreset.roomCount = roomCount.value!!.toInt()
                createdPreset.hasBathroom = hasBathroom.value!!
                createdPreset.hasSink = hasSink.value!!
                createdPreset.hasKitchen = hasKitchen.value!!
                createdPreset.isFurnished = isFurnished.value!!
                createdPreset.nameOfPreset = presetName.value!!

                database.updateDorm(createdPreset)
                _navigateToMenu.value = true
            }
        }
    }

    fun doneNavigating(){
        _navigateToMenu.value = null
    }
}