package com.example.navbasics.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material.icons.filled.ScreenShare
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

//constants you do not want to hard code, this class is not mandatory
//it just helps you not to make mistakes while typing the route or title etc...
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    //    declare objects
    object CartScreen : Screen("cart/{count}/{fruit}", "Cart", Icons.Default.ShoppingCart)
    object FirstScreen : Screen("first-screen", "First Screen", Icons.Default.ScreenShare)
    object SecondScreen : Screen("second-screen", "Second Screen", Icons.Default.ScreenRotation)
}
