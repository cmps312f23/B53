package com.example.stadiums.repo

import android.content.Context
import com.example.stadiums.model.Stadium
import kotlinx.serialization.json.Json

object StadiumRepo {
    var stadiums = listOf<Stadium>()

    fun getStadiums(context: Context): List<Stadium> {
        if(stadiums.isEmpty()) {
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
}