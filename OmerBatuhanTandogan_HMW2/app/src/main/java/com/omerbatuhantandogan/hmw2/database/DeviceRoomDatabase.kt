package com.omerbatuhantandogan.hmw2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omerbatuhantandogan.hmw2.Constants

@Database(entities = [Device::class], version = 4)
abstract class DeviceRoomDatabase : RoomDatabase()  {
    abstract fun deviceDao(): DeviceDAO

    companion object{
        @Volatile
        private var INSTANCE:DeviceRoomDatabase?=null

        fun getDatabase(context: Context):DeviceRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }

            synchronized(this){
                val  instance = Room.databaseBuilder(context.applicationContext, DeviceRoomDatabase::class.java, Constants.DATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}