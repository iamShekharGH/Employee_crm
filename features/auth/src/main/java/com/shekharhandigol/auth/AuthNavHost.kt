package com.shekharhandigol.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.auth.login.LoginScreen
import com.shekharhandigol.auth.splash.SplashScreen
import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object AuthModule : Destinations()

    @Serializable
    data object LoginScreen : Destinations()

    @Serializable
    data object Splash : Destinations()
}


fun NavGraphBuilder.authNavGraph(navController: NavController) {

    navigation<Destinations.AuthModule>(startDestination = Destinations.Splash) {
        composable<Destinations.Splash> {
            SplashScreen {
                navController.navigate(Destinations.LoginScreen)
            }
        }
        composable<Destinations.LoginScreen> {
            LoginScreen()
        }
    }
}
