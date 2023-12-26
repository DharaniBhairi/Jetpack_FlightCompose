package com.example.flightcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flightcompose.ui.theme.FlightComposeTheme
import com.example.flightcompose.ui.theme.screens.Details
import com.example.flightcompose.ui.theme.screens.Flight
import com.example.flightcompose.ui.theme.screens.Home
import com.example.flightcompose.ui.theme.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlightComposeTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   Scaffold (
                       bottomBar = {
                           BottomNavigationBar(navController = navController)
                       }, content = {
                           padding -> NavHostContainer(
                           navController = navController,
                           padding = padding
                       )
                       }
                   )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlightComposeTheme {
        Surface(Modifier.fillMaxSize()) {
        }
    }
}

@Composable
fun NavHostContainer(navController: NavHostController,padding: PaddingValues) {
    NavHost(navController = navController, startDestination = "home", builder = {
        composable("home"){
            Home()
        }
        composable("cart"){
            Flight()
        }
        composable("details"){
            Details()
        }
    })
}

@Composable
fun BottomNavigationBar(navController: NavHostController){
    BottomNavigation( backgroundColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Constants.bottomNavItems.forEach {navItem ->
            BottomNavigationItem(selected = currentRoute == navItem.route, onClick = {navController.navigate(navItem.route)},
                icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label, modifier = Modifier.size(30.dp))},
                selectedContentColor = Color(0xFF4cc9f0),
                unselectedContentColor = Color.Gray
            )
        }
    }
}