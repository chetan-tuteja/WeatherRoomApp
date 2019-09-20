package com.chetantuteja.extraaedge.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chetantuteja.extraaedge.datamodels.Weather

@Database(entities = [(Weather::class)],version = 1)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDAO(): WeatherDAO
}