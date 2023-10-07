package com.example.stadiums.repo

import android.content.Context
import com.example.stadiums.model.Stadium
import kotlinx.serialization.json.Json

object SortOptions {
    const val NAME = "NAME"
    const val CITY = "CITY"
    const val CAPACITY = "CAPACITY"
    const val DEFAULT = "DEFAULT"
}

object StadiumRepo {
    var stadiums = listOf<Stadium>()

    fun getStadiums(context: Context): List<Stadium> {
        if (stadiums.isEmpty()) {
            // read the stadium.json data from the assets folder
            val jsonTextOfTheFile = context
                .assets
                .open("stadiums.json")
                .bufferedReader()
                .use { it.readText() }

            // decode them to Stadium objects
            stadiums = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonTextOfTheFile)
        }
        return stadiums
    }

    fun getFilteredStadium(query: String) = stadiums.filter {
        it.name.contains(query, ignoreCase = true) or it.city.contains(query, ignoreCase = true)
    }

    fun sortStadiums(sortOption: String) = when (sortOption) {
        SortOptions.NAME -> stadiums.sortedBy { it.name }
        SortOptions.CITY -> stadiums.sortedBy { it.city }
        SortOptions.CAPACITY -> stadiums.sortedBy { it.seatingCapacity }
        else -> stadiums
    }

}
