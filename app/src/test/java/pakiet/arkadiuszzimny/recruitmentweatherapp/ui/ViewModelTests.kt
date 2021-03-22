package pakiet.arkadiuszzimny.recruitmentweatherapp.ui

import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Test
import pakiet.arkadiuszzimny.recruitmentweatherapp.model.data.WeatherResponses

class ViewModelTests {

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

    private lateinit var arrayData: WeatherResponses
    private lateinit var viewModel: MainViewModel

    @Before
    fun setArrayData() {
        arrayData = GsonBuilder().create().fromJson(json, WeatherResponses::class.java)
        viewModel = MainViewModel()
    }

    @Test
    fun should_get_smallest_temp() {
        //when
        var value = viewModel.getSmallestAcross(arrayData)
        //then
        assertThat(value).isEqualTo(-6.0)
    }

    @Test
    fun should_get_city_with_smallest_average() {
        //when
        var value = viewModel.getCityWithSmallestAverageFun(arrayData)
        //then
        assertThat(value).isEqualTo("Warsaw")
    }

    @Test
    fun should_get_array_with_city() {
        //when
        var array = viewModel.getArrayWithCity(arrayData)
        //then
        assertThat(array).isEqualTo(arrayOf("Warsaw", "Paris", "Berlin", "New York"))
    }

    @Test
    fun should_get_array_with_max_temp() {
        //when
        val array = viewModel.getArrayWithMaxTemp(arrayData)
        //then
        assertThat(array).isEqualTo(arrayOf(3.0, 22.0, 5.5, 16.0))
    }

    @Test
    fun should_get_array_with_weather() {
        //when
        val array = viewModel.getArrayWithWeather(arrayData)
        //then
        assertThat(array).isEqualTo(arrayOf("rainy", "cloudy", "sunny", "cloudy"))
    }

    @Test
    fun should_get_array_with_smallest_temp() {
        //when
        val array = viewModel.getArrayWithSmallestTemp(arrayData)
        //then
        assertThat(array).isEqualTo(arrayOf(-2.0, 11.0, -6.0, 12.0))
    }

}