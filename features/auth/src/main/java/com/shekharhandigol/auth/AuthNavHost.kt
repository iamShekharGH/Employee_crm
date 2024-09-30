package com.shekharhandigol.auth

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.auth.login.LoginScreen
import com.shekharhandigol.auth.splash.SplashScreen
import kotlinx.serialization.Serializable

sealed class AuthDestinations {

    @Serializable
    data object AuthModule : AuthDestinations()

    @Serializable
    data object LoginScreen : AuthDestinations()

    @Serializable
    data object Splash : AuthDestinations()
}


fun NavGraphBuilder.authNavGraph(navController: NavController, onSignInClick: () -> Unit) {

    navigation<AuthDestinations.AuthModule>(startDestination = AuthDestinations.Splash) {
        composable<AuthDestinations.Splash> {
            SplashScreen {
                navController.navigate(AuthDestinations.LoginScreen)
            }
        }
        composable<AuthDestinations.LoginScreen> {
            LoginScreen(viewModel = hiltViewModel(), onSignInClick = onSignInClick)
        }
    }
}
