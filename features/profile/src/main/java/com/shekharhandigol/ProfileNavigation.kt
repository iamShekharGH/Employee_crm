package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.common.Destinations
import com.shekharhandigol.profile.ProfileScreen


fun NavGraphBuilder.profileNavGraph(navController: NavController, goToHome: () -> Unit) {
    navigation<Destinations.ProfileModule>(startDestination = Destinations.Profile(-1)) {
        composable<Destinations.Profile> {
            ProfileScreen(goToHome)
        }
    }

}