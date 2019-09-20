package com.chetantuteja.extraaedge.ViewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import com.chetantuteja.extraaedge.Database.WeatherDatabase
import com.chetantuteja.extraaedge.Repository.MainActivityRepo
import com.chetantuteja.extraaedge.datamodels.Weather

class MainActivityViewModel: ViewModel() {
    lateinit var repo: MainActivityRepo

    init {
        repo = MainActivityRepo()
    }

    fun getWeatherData(): LiveData<List<Weather>>{
        return repo.getWeather()
    }

    fun fetchWeatherAndStore(){
        repo.fetchAPIAndStore()
    }
}