package com.example.navbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ImageAspectRatio
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
//"cart/20"

//cart/{count}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val screens = listOf(
        Screen.FirstScreen,
        Screen.SecondScreen,
        Screen.CartScreen
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEach {
                    NavigationBarItem(
                        selected = GetCurrentRoute(navController) == it.route,
                        onClick = { navController.navigate(it.route) },
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = ""
                            )
                        },
                        label = { Text(text = it.title) },
                        alwaysShowLabel = GetCurrentRoute(navController) == it.route
                    )
                }

            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "My App Name") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        Modifier.padding(10.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = ""
                    )
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "",
                        Modifier.padding(10.dp).clickable {
                            navController.navigate("cart/40/Orange")
                        }
                    )
                }
            )
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