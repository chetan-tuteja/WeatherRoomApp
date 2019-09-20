package com.chetantuteja.extraaedge.ViewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.chetantuteja.extraaedge.Database.WeatherDatabase

class RoomViewModel {

    companion object {
        @Volatile var instance: WeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            WeatherDatabase::class.java, "weather_db").fallbackToDestructiveMigration()
            .build()
    }

}