package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.profile.ProfileScreen
import kotlinx.serialization.Serializable


sealed class Destinations {

    @Serializable
    data object Profile : Destinations()

    @Serializable
    data object ProfileModule : Destinations()
}


fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    navigation<Destinations.ProfileModule>(startDestination = Destinations.Profile) {
        composable<Destinations.Profile> {
            ProfileScreen()
        }
    }

}

class ProfileNavigation {
}