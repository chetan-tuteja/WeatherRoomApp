package com.chetantuteja.extraaedge

import com.chetantuteja.extraaedge.datamodels.WeatherFeed
import retrofit2.Call
import retrofit2.http.GET

interface WeatherAPI {

    //NOTE: As The E-Mail mentioned to fetch "Weather results" so I'm only fetching the data model for same.
    //If Needed we can add more data models as per requirement.
    @GET("weather?q=Pune&appid=812d7c7e3a5d6fe618a0495036f8ced9&units=imperial")
    fun getWeather(): Call<WeatherFeed>
}