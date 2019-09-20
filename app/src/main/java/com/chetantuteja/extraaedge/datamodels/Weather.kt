package com.chetantuteja.extraaedge.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Weather")
data class Weather(

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("main")
    @Expose
    val main:String,

    @SerializedName("description")
    @Expose
    val description:String,

    @SerializedName("icon")
    @Expose
    val icon:String
)