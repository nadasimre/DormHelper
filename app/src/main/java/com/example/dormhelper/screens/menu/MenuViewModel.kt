package com.example.dormhelper.screens.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dormhelper.database.UserDatabaseDAO
import com.example.dormhelper.model.DormPreset
import com.example.dormhelper.model.User
import kotlinx.coroutines.launch
import kotlin.random.Random

class MenuViewModel(user: User, val database: UserDatabaseDAO) : ViewModel() {

    var username = MutableLiveData<String>()
    var email = MutableLiveData<String>()

    private val _navigateToPreset = MutableLiveData<DormPreset?>()
    val navigateToPreset: LiveData<DormPreset?>
        get() = _navigateToPreset

    private val _navigateToPresetBuilder = MutableLiveData<DormPreset?>()
    val navigateToPresetBuilder: LiveData<DormPreset?>
        get() = _navigateToPresetBuilder

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean>
        get() = _showDialog

    init{
        username.value = user.userName
        email.value = user.email
    }

    val presetList = database.getDorms(username.value!!)

    fun addPreset(){
        viewModelScope.launch{
            val newPreset = DormPreset(
                createId(Random.nextInt(0, 100)),
                nameOfPreset = "Not configured!",
                nameOfUser = username.value!!,
                size = 0,
                roomCount = 0,
                hasBathroom = false,
                hasSink = false,
                hasKitchen = false,
                isFurnished = false
            )
            database.insertDorm(newPreset)
            _navigateToPresetBuilder.value = newPreset
        }
    }

    private fun createId(num: Int): Int{
        return num.hashCode() * Random.nextInt(0, 100)
    }

    fun navigatedToBuilder(){
        _navigateToPresetBuilder.value = null
    }

    fun onPresetClicked(id: Int){
        viewModelScope.launch{
            _navigateToPreset.value = database.getDorm(id)
        }
    }

    fun onPresetNavigated(){
        _navigateToPreset.value = null
    }

    fun clearDialog(){
        _showDialog.value = true
    }

    fun cleared(){
        _showDialog.value = false
    }

    fun clearPresets(){
        viewModelScope.launch{
            database.clearDorms(username.value!!)
        }
    }
}