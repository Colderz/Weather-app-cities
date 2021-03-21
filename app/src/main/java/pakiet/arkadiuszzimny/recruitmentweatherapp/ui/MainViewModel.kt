package pakiet.arkadiuszzimny.recruitmentweatherapp.ui

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import pakiet.arkadiuszzimny.recruitmentweatherapp.WeatherResponseItem
import pakiet.arkadiuszzimny.recruitmentweatherapp.WeatherResponses
import java.util.stream.Stream

class MainViewModel : ViewModel() {

    val json = """
    [
       {
          "city":"Warsaw",
          "weather":"rainy",
          "hourly_temp":[
             { "temp":-2, "hour":0 },
             { "temp":-2, "hour":4 },
             { "temp":0.5, "hour":8 },
             { "temp":2, "hour":12 },
             { "temp":3, "hour":16 },
             { "temp":-1, "hour":20 }
          ]
       },
       {
          "city":"Paris",
          "weather":"cloudy",
          "hourly_temp":[
             { "temp":11, "hour":0 },
             { "temp":14, "hour":4 },
             { "temp":18, "hour":8 },
             { "temp":22, "hour":12 },
             { "temp":15, "hour":16 },
             { "temp":13, "hour":20 }
          ]
       },
       {
          "city":"Berlin",
          "weather":"sunny",
          "hourly_temp":[
             { "temp":-6, "hour":0 },
             { "temp":-4, "hour":4 },
             { "temp":2, "hour":8 },
             { "temp":4, "hour":12 },
             { "temp":5.5, "hour":16 },
             { "temp":3, "hour":20 }
          ]
       },
       {
          "city":"New York",
          "weather":"cloudy",
          "hourly_temp":[
             { "temp":12, "hour":0 },
             { "temp":13, "hour":4 },
             { "temp":12, "hour":8 },
             { "temp":15, "hour":12 },
             { "temp":16, "hour":16 },
             { "temp":14, "hour":20 }
          ]
       }
    ]
""".trimIndent()

    var weatherItems = GsonBuilder().create().fromJson(json, WeatherResponses::class.java)
    val arraySize = weatherItems.size

    fun getSmallestAcross(): Double {
        return (Array(arraySize) { i ->
            weatherItems[i].hourly_temp.minByOrNull { it.temp }.let { it!!.temp }
        }).minByOrNull { it }!!
    }

    fun getCityWithSmallestAverage(): String {
        val arrayAvg =
            Array(arraySize) { i -> weatherItems[i].hourly_temp.map { it.temp }.average() }
        val arrayCity = Array(arraySize) { i -> weatherItems[i].city }
        return arrayAvg.mapIndexedNotNull { index, d -> if (d == arrayAvg.minByOrNull { it }) arrayCity[index] else null }
            .first()
    }

}