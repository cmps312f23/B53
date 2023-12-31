package com.cmps312.stockmarketapp.ui.screens.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cmps312.stockmarketapp.ui.screens.components.getCurrentRoute
import com.cmps312.stockmarketapp.AppNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    //remember navController so it does not get recreated on recomposition
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) {
        paddingValues -> AppNavigator(navController = navController, padding = paddingValues)
    }
}

/**
 * It receives navcontroller to navigate between screens
 */
@Composable
fun BottomNavBar(navController: NavHostController) {
    BottomAppBar {
        //observe current route to change the icon color,label color when navigated
        val currentRoute = getCurrentRoute(navController)
        val navItems = listOf(
            Screen.StockQuote,
            Screen.StockQuotes
        )

        navItems.forEach { navItem ->
            NavigationBarItem(
                //if currentRoute is equal to the nav item route then set selected to true
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        /* Navigate to the destination only if we’re not already on it,
                        avoiding multiple copies of the destination screen on the back stack */
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
                alwaysShowLabel = false
            )
        }
    }
}