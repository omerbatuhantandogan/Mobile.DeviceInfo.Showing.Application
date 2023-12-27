package com.omerbatuhantandogan.hmw2.database

import androidx.lifecycle.LiveData

class DeviceRepository(private val deviceDAO: DeviceDAO) {
    val readAlldata: LiveData<List<Device>> = deviceDAO.getAllDevices()

    fun insertDevice(device:Device){
        deviceDAO.insertDevice(device)
    }
    fun insertDevices(devices:ArrayList<Device>){
        deviceDAO.insertAllDevices(devices)
    }

    fun updateDevice(device:Device){
        deviceDAO.updateDevice(device)
    }

    fun deleteDevice(device:Device){
        deviceDAO.deleteDevice(device)
    }

    fun deleteAllDevices(){
        deviceDAO.deleteAllDevices()
    }

    fun getAllDevices(): LiveData<List<Device>> {
        return deviceDAO.getAllDevices()
    }

    fun getDeviceById(id:Int):Device{
        return deviceDAO.getDeviceById(id)
    }
}
