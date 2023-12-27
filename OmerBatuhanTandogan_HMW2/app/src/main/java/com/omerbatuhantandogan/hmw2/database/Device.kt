package com.omerbatuhantandogan.hmw2.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.omerbatuhantandogan.hmw2.Constants

@Entity(tableName = Constants.TABLENAME)
class Device
    (@PrimaryKey(autoGenerate = true)
             var id:Int = 0,
             @ColumnInfo(name = "deviceName")
             var name:String,
             @ColumnInfo(name = "deviceType")
             var type: String,
             @ColumnInfo(name = "devicePrice")
             var price: Double) : Parcelable
{
    constructor(parcel: Parcel) : this(parcel.readInt(),parcel.readString().toString(),parcel.readString().toString(),parcel.readDouble())
    constructor(name: String, type: String,price: Double) : this(0,name = name, type = type,price = price)

    override fun toString(): String {
        return  "\nDeviceName='$name'" +
                "\nDeviceType='$type'" +
                "\nDevicePrice='$price' TL"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Device> {
        override fun createFromParcel(parcel: Parcel): Device {
            return Device(parcel)
        }

        override fun newArray(size: Int): Array<Device?> {
            return arrayOfNulls(size)
        }
    }
}