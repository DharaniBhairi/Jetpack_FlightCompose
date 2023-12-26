package com.example.flightcompose.ui.theme.utils


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.flightcompose.ui.theme.model.BottomNavItem

object Constants {
    val bottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Default.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Flight",
            icon = Icons.Default.ShoppingCart,
            route = "cart"
        ),
        BottomNavItem(
            label = "Details",
            icon = Icons.Default.AccountBox,
            route = "details"
        )
    )
}