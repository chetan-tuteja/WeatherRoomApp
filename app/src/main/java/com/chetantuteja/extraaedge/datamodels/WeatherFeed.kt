package com.chetantuteja.extraaedge.datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherFeed(

    @SerializedName("weather")
    @Expose
    val weather: List<Weather>
)