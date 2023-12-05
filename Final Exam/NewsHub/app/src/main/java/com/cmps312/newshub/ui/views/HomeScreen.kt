package com.cmps312.newshub.ui.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.cmps312.newshub.repository.NewsHubRepo

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    var articles by remember {
        mutableStateOf(NewsHubRepo.getArticles(context))
    }
    var showAddScreen by remember {
        mutableStateOf(false)
    }
    val TAG = "MyApp"
    Log.d(TAG, "MyApp: $articles")
    Scaffold(
        topBar = {
            if (!showAddScreen)
                TopBar {
                    articles = it
                }
        },
        floatingActionButton = {
            if (!showAddScreen)
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        Modifier.clickable { showAddScreen = true })
                }
        }
    ) {
        if (!showAddScreen)
            ArticleList(articles = articles, it)
        else
            AddNewsArticle() {
                articles = articles + it
                showAddScreen = false
            }
    }
}