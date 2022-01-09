package com.example.dormhelper.screens.dormpresetdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dormhelper.database.UserDatabaseDAO
import com.example.dormhelper.model.DormPreset
import kotlinx.coroutines.launch

class PresetDetailViewModel(var DormPreset: DormPreset, val database: UserDatabaseDAO): ViewModel() {

    val id = DormPreset.id

    var presetName = MutableLiveData<String>()
    var presetSize = MutableLiveData<String>()
    var presetRooms = MutableLiveData<String>()

    var bathroomVisibility = MutableLiveData<Boolean>()
    var sinkVisibility = MutableLiveData<Boolean>()
    var kitchenVisibility = MutableLiveData<Boolean>()
    var furnitureVisibility = MutableLiveData<Boolean>()

    private var _onDeletePreset = MutableLiveData<Boolean>()
    val onDeletePreset: LiveData<Boolean>
        get() = _onDeletePreset

    init{
        viewModelScope.launch{
            presetName.value = DormPreset.nameOfPreset
            presetSize.value = DormPreset.size.toString()
            presetRooms.value = DormPreset.roomCount.toString()
            bathroomVisibility.value = DormPreset.hasBathroom
            sinkVisibility.value = DormPreset.hasSink
            kitchenVisibility.value = DormPreset.hasKitchen
            furnitureVisibility.value = DormPreset.isFurnished
        }
    }

    fun onDelete(){
        viewModelScope.launch {
            database.deleteDorm(id)
            _onDeletePreset.value = true
        }
    }

    fun afterDeleteNavigated(){
        _onDeletePreset.value = false
    }
}