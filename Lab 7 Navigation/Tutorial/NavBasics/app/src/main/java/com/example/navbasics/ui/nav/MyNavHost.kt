package com.example.navbasics.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.navbasics.ui.screen.CartScreen
import com.example.navbasics.ui.screen.FirstScreen
import com.example.navbasics.ui.screen.Screen
import com.example.navbasics.ui.screen.SecondScreen

@Composable
fun MyNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.FirstScreen.route
    ) {

        composable(route = Screen.FirstScreen.route) {
            FirstScreen()
        }

        composable(route = Screen.SecondScreen.route) {
            SecondScreen()
        }

        composable(route = Screen.CartScreen.route,
            arguments = listOf(
                navArgument("count") { type = NavType.IntType },
                navArgument("fruit") { type = NavType.StringType }
            )
        ) {
            val count = it.arguments?.getInt("count")
            val fruit = it.arguments?.getString("fruit")
            CartScreen(count = count ,  fruit = fruit)
        }
    }
}