package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.home.HomeScreen
import kotlinx.serialization.Serializable

sealed class HomeDestination {

    @Serializable
    data object HomeModule : HomeDestination()

    @Serializable
    data object Home : HomeDestination()
}

fun NavGraphBuilder.homeNavigationGraph(navController: NavController) {
    navigation<HomeDestination.HomeModule>(startDestination = HomeDestination.Home) {
        composable<HomeDestination.Home> {
            HomeScreen()
        }
    }
}