package com.javidev.eltiempo.data.repository

import android.util.Log
import com.javidev.eltiempo.data.dataSource.DataOrException
import com.javidev.eltiempo.data.model.Weather
import com.javidev.eltiempo.data.network.WeatherApi
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String, units: String)
            : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            Log.d("REPO", "dentro del TRY")

            api.getWeather(query = cityQuery, units = units)

        } catch (e: Exception) {
            Log.d("REPO", "Excepction: $e")
            return DataOrException(e = e)
        }

        Log.d("REPO", "valor del response: $response")
        return DataOrException(data = response)

    }

}