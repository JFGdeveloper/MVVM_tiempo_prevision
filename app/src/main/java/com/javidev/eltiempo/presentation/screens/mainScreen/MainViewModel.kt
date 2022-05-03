package com.javidev.eltiempo.presentation.screens.mainScreen

import androidx.lifecycle.ViewModel
import com.javidev.eltiempo.data.dataSource.DataOrException
import com.javidev.eltiempo.data.model.Weather
import com.javidev.eltiempo.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: WeatherRepository): ViewModel(){

    suspend fun getWeatherData(city: String, units: String)
            : DataOrException<Weather, Boolean, Exception> {
        return repo.getWeather(cityQuery = city, units = units)

    }


}