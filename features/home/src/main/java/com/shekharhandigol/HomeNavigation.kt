package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.home.HomeScreen
import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object HomeModule : Destination()

    @Serializable
    data object Home : Destination()
}

fun NavGraphBuilder.homeNavigationGraph(navController: NavController) {
    navigation<Destination.HomeModule>(startDestination = Destination.Home) {
        composable<Destination.Home> {
            HomeScreen()
        }
    }
}