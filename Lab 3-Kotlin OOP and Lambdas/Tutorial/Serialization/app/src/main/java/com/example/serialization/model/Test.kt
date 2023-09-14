package com.example.serialization.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.math.log


@Serializable
data class Data(val a: Int, val b: String)
@Serializable
data class CovidStat(
    var id: Int,
    var country: String,
    var continent: String,
    var region: String,
    var totalCases: Int?,
    var totalDeaths: Int?,
    var newDeaths: Int?,
    var totalRecovered: Int?,
    var newRecovered: Int?,
    var activeCases: Int?,
    var population: Int?
)

fun main() {
    val data = Data(42, "str")
    val json = Json.encodeToString(data)
    println(json)
    val obj = Json.decodeFromString<Data>("""{"a":42, "b": "str"}""")
    println(obj.a)

    val covidDataText = File("data/covid-data.json").readText()


    val listOfCovidData = Json{ignoreUnknownKeys = true}
         .decodeFromString<List<CovidStat>>(covidDataText)
    listOfCovidData.forEach {
        println(it)
    }
}