package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.profile.ProfileScreen
import kotlinx.serialization.Serializable


sealed class ProfileDestinations {

    @Serializable
    data object Profile : ProfileDestinations()

    @Serializable
    data object ProfileModule : ProfileDestinations()
}


fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    navigation<ProfileDestinations.ProfileModule>(startDestination = ProfileDestinations.Profile) {
        composable<ProfileDestinations.Profile> {
            ProfileScreen()
        }
    }

}

class ProfileNavigation {
}