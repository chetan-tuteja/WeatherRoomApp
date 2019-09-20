package com.chetantuteja.extraaedge.Repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.chetantuteja.extraaedge.MainActivity
import com.chetantuteja.extraaedge.ViewModels.RoomViewModel
import com.chetantuteja.extraaedge.WeatherAPI
import com.chetantuteja.extraaedge.datamodels.Weather
import com.chetantuteja.extraaedge.datamodels.WeatherFeed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityRepo {
    companion object{
        const val TAG = "MainActivityRepo"
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    fun getWeather(): LiveData<List<Weather>>{
        return RoomViewModel.instance!!.weatherDAO().getAllWeather()
    }

    fun fetchAPIAndStore(){
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherFeed = retrofit.create(WeatherAPI::class.java)

        val feed = weatherFeed.getWeather()
        feed.enqueue(object : Callback<WeatherFeed> {
            override fun onFailure(call: Call<WeatherFeed>, t: Throwable) {
                Log.e(TAG, "onFailure: Unable to get data " + t.message.toString())
            }

            override fun onResponse(call: Call<WeatherFeed>, response: Response<WeatherFeed>) {
                Log.d(TAG, "onResponse:  Server Response: $response")
                when(response.code()){
                    200 -> {
                        val t = Thread{
                            val db = RoomViewModel()
                            RoomViewModel.instance!!.weatherDAO().deleteAllWeatherData()
                            RoomViewModel.instance!!.weatherDAO().insertIntoWeather(response.body()!!.weather)
                        }
                        t.start()
                    }
                }

            }

        })
    }
}