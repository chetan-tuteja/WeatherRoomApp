package com.chetantuteja.extraaedge.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chetantuteja.extraaedge.datamodels.Weather

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM Weather")
    fun getAllWeather(): LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoWeather(weatherList: List<Weather>)

    @Query("DELETE FROM Weather")
    fun deleteAllWeatherData()
}