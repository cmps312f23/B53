package com.example.stadiums

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.stadiums.repo.StadiumRepo
import com.example.stadiums.ui.theme.StadiumsTheme
import com.example.stadiums.view.StadiumList
import com.example.stadiums.view.TopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            StadiumsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var context = LocalContext.current
    var stadiums by remember {
        mutableStateOf(StadiumRepo.getStadiums(context))
    }

    Scaffold(
        topBar = {
            TopBar(
                filterBy = {
                    val TAG = "Filter By"
                    Log.d(TAG, "MyApp: $it")
                    stadiums = StadiumRepo.getFilteredStadium(it)
                },
                sortBy = {
                    stadiums = StadiumRepo.sortStadiums(it)
                }
            )
        },
        content = {
            StadiumList(
                stadiums = stadiums,
                modifier = modifier.padding(it)
            )
        }
    )
}
