package com.omerbatuhantandogan.hmw2.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeviceViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Device>>
    private val repository:DeviceRepository
    init {
        val deviceDao= DeviceRoomDatabase.getDatabase(application).deviceDao()
        repository = DeviceRepository(deviceDao)
        readAllData = repository.readAlldata
    }
    fun getAllDevices(): LiveData<List<Device>> {
        return repository.getAllDevices()
    }

    fun addDevice(device:Device){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertDevice(device)
        }
    }
    fun addDevices(devices: List<Device>){
        viewModelScope.launch(Dispatchers.IO) {
            devices.forEach{
                repository.insertDevice(it)
            }
        }
    }
    fun deleteDevice(device:Device){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteDevice(device)
        }
    }
    fun deleteAllDevices(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllDevices()
        }
    }
    fun updateDevice(device:Device){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateDevice(device)
        }
    }
}