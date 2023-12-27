package com.omerbatuhantandogan.hmw2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omerbatuhantandogan.hmw2.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevice(device: Device)

    @Update
    fun updateDevice(device: Device)

    @Delete
    fun deleteDevice(device: Device)

    @Query("DELETE FROM ${Constants.TABLENAME}")
    fun deleteAllDevices()

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY id DESC")
    fun getAllDevices(): LiveData<List<Device>>

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE id =:id")
    fun getDeviceById(id:Int):Device

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDevices(devices: ArrayList<Device>){
        devices.forEach{
            insertDevice(it)
        }
    }
}