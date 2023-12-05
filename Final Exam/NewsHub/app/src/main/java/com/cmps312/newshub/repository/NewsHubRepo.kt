package com.cmps312.newshub.repository

import android.content.Context
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import kotlinx.serialization.json.Json

object NewsHubRepo {
    private var articles = mutableListOf<Article>()
    private var categories = mutableListOf<Category>()

    fun getArticles(context: Context): List<Article> {
//        read from the assets folder
        val jsonText = context
            .assets
            .open("articles.json")
            .bufferedReader().use { it.readText() }

        articles = Json { ignoreUnknownKeys = true }
            .decodeFromString(jsonText)

        return articles

    }

    fun getCategories(context: Context): List<Category> {
//        read from the assets folder
        val jsonText = context
            .assets
            .open("categories.json")
            .bufferedReader().use { it.readText() }

        categories = Json { ignoreUnknownKeys = true }
            .decodeFromString(jsonText)

        return categories

    }

    fun getCategory(context: Context , categoryId: Int) = getCategories(context).find { it.id == categoryId }

    fun filterArticles(context: Context, categoryId: Int): List<Article> = getArticles(context)
        .filter { it.categoryId == categoryId }



//    Below this write all the methods needed for the app to work.
}