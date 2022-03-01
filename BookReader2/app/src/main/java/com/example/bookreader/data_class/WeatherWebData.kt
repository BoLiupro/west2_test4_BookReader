package com.example.bookreader.data_class

data class WeatherWebData(val cede: String, val updateTime: String,
                          val fxLink: String, val now: Now
){
    data class Now(val obsTime: String, val temp: String, val feelsLke: String)
}

