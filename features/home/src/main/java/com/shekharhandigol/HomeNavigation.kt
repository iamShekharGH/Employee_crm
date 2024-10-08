package com.shekharhandigol

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.common.Destinations
import com.shekharhandigol.home.HomeScreen


fun NavGraphBuilder.homeNavigationGraph(
    navController: NavController,
    goToProfile: () -> Unit,
    openDrawer: () -> suspend () -> Unit
) {
    navigation<Destinations.HomeModule>(startDestination = Destinations.Home) {
        composable<Destinations.Home> {
            HomeScreen(
                goToProfile = goToProfile,
                openDrawer = openDrawer,
                viewModel = hiltViewModel()
            )
        }
    }
}