package pakiet.arkadiuszzimny.recruitmentweatherapp.ui

import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import pakiet.arkadiuszzimny.recruitmentweatherapp.model.data.WeatherResponses

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

    fun getSmallestAcross(): Double {
        return (Array(weatherItems.size) { i ->
            weatherItems[i].hourly_temp.minByOrNull { it.temp }.let { it!!.temp }
        }).minByOrNull { it }!!
    }

    /**
     * "Clear" version with separate boards
     */
    /*fun getCityWithSmallestAverage(): String {
        val arrayAvg: Array<Double> =
            Array(arraySize) { i -> weatherItems[i].hourly_temp.map { it.temp }.average() }
        val arrayCity = Array(arraySize) { i -> weatherItems[i].city }
        return arrayAvg.mapIndexedNotNull { index, d -> if (d == arrayAvg.minByOrNull { it }) arrayCity[index] else null }
            .first()
    }*/

    /**
     * The most * FUNCTIONAL * version but the most CPU-intensive
     */
    fun getCityWithSmallestAverageFun(): String {
        return weatherItems.mapNotNull { d ->
            if (d.hourly_temp.map { it.temp }.average() == Array(weatherItems.size) { i ->
                    d.hourly_temp.map { it.temp }.average()
                }.minByOrNull { it }
            ) d.city else null
        }.first()
    }

    /**
     * Separate array for passing to the recyclerView adapter
     */
    fun getArrayWithCity(): Array<String> {
        return Array(weatherItems.size) { i -> weatherItems[i].city }
    }

    /**
     * Separate array for passing to the recyclerView adapter
     */
    fun getArrayWithMaxTemp(): Array<Double> {
        return (Array(weatherItems.size) { i ->
            weatherItems[i].hourly_temp.maxByOrNull { it.temp }.let { it!!.temp }
        })
    }

    /**
     * Separate array for passing to the recyclerView adapter
     */
    fun getArrayWithWeather(): Array<String> {
        return Array(weatherItems.size) { i -> weatherItems[i].weather }
    }

    /**
     * Separate array for passing to the recyclerView adapter
     */
    fun getArrayWithSmallestTemp(): Array<Double> {
        return (Array(weatherItems.size) { i ->
            weatherItems[i].hourly_temp.minByOrNull { it.temp }.let { it!!.temp }
        })
    }

    /**
     * Find the smallest temperature across all cities and print it
     */
    fun printSmallestTemp() {
        println("The smallest temperature across all cities is -> ${getSmallestAcross()} °C!")
    }

    /**
     * For each city find its highest temperatures and print the results
    in format "city: max_temp"
     */
    fun printMaxTempForEachCity() {
        println("Highest temperatures:")
        weatherItems.forEach {
            println(
                "${it.city}: ${
                    it.hourly_temp.maxByOrNull { it.temp }.let { it!!.temp }
                } °C!"
            )
        }
    }

    /**
     *  Find the city with the smallest average daily temperature and
    print its name
     */
    fun printCityWithSmallestAvg() {
        println("The city with the smallest average daily temperature -> ${getCityWithSmallestAverageFun()}!")
    }

}