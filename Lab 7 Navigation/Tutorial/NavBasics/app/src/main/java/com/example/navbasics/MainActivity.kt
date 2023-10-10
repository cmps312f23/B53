package com.example.navbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageAspectRatio
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navbasics.ui.nav.MyNavHost
import com.example.navbasics.ui.screen.FirstScreen
import com.example.navbasics.ui.screen.Screen
import com.example.navbasics.ui.screen.SecondScreen
import com.example.navbasics.ui.theme.NavBasicsTheme
import com.example.navbasics.ui.utils.GetCurrentRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavBasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp("Android")
                }
            }
        }
    }
}

@Composable
fun MyApp(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = GetCurrentRoute(navController) == Screen.FirstScreen.route,
                    onClick = { navController.navigate(Screen.FirstScreen.route)},
                    icon = {
                           Icon(imageVector = Screen.FirstScreen.icon,
                               contentDescription = "")
                    },
                    label = { Text(text = Screen.FirstScreen.title)}
                )
                NavigationBarItem(
                    selected = GetCurrentRoute(navController)  == Screen.SecondScreen.route,
                    onClick = { navController.navigate(Screen.SecondScreen.route)},
                    icon = {
                           Icon(imageVector = Screen.SecondScreen.icon,
                               contentDescription = "")
                    },
                    label = { Text(text = Screen.SecondScreen.title)}
                )
            }
        }
    ) {
        MyNavHost(navHostController = navController, paddingValues = it)
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    NavBasicsTheme {
        MyApp("Android")
    }
}