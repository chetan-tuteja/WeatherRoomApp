package com.chetantuteja.extraaedge

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.chetantuteja.extraaedge.Repository.MainActivityRepo
import com.chetantuteja.extraaedge.ViewModels.MainActivityViewModel
import com.chetantuteja.extraaedge.ViewModels.RoomViewModel
import com.chetantuteja.extraaedge.datamodels.Weather
import com.chetantuteja.extraaedge.datamodels.WeatherFeed
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //NOTE: As The E-Mail mentioned to fetch "Weather results" so I'm only fetching the data model for same.
    //If Needed we can add more data models as per requirement.

    companion object{
        const val TAG = "MainActivity"
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    //Variables
    lateinit var mViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        RoomViewModel(this)
        setupWeatherData()
    }

    private fun setupWeatherData(){
        if(isNetworkAvailable()){
            Log.d(TAG, "setupWeatherData: Entered fetching")
            mViewModel.fetchWeatherAndStore()
        } else {
            //Toast.makeText(this@MainActivity,"No Internet Found.",Toast.LENGTH_LONG).show()
            popupDialog()
        }

        mViewModel.getWeatherData().observe(this, Observer { dataList ->
            Log.d(TAG, "setupWeatherData: The Data is $dataList")
            setupViewData(dataList)
        })
    }

    private fun setupViewData(dataList: List<Weather>) {
        val icon = "wi_owm_${dataList[0].id}"
        val iconID = resources.getIdentifier(icon,"string",packageName)

        cardCityName.text = "Pune"
        cardWeatherIcon.setIconResource(getString(iconID))
        cardWeatherDesc.text = dataList[0].description.toUpperCase()

    }


    private fun isNetworkAvailable(): Boolean {
        val cManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeInfo = cManager.activeNetwork
        if (activeInfo != null) {
            val cap = cManager.getNetworkCapabilities(activeInfo)
            if (cap != null) {
                cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                return true
            }
        }
        return false
    }

    private fun popupDialog() {
        MaterialDialog(this@MainActivity).show {
            title(R.string.error)
            message(R.string.no_internet)
            positiveButton(R.string.ok_btn) { dialog ->
                dialog.dismiss()
            }
            cornerRadius(16f)
        }
    }




}
