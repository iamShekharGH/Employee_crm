package com.shekharhandigol.auth

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.auth.login.LoginScreen
import com.shekharhandigol.auth.splash.SplashScreen
import com.shekharhandigol.common.Destinations


fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    onSignInClick: () -> Unit,
    goToHome: () -> Unit
) {

    navigation<Destinations.AuthModule>(startDestination = Destinations.Splash) {
        composable<Destinations.Splash> {
            SplashScreen(
                { navController.navigate(Destinations.LoginScreen) },
                goToHome,
            )
        }
        composable<Destinations.LoginScreen> {
            LoginScreen(
                viewModel = hiltViewModel(),
                onSignInClick = onSignInClick,
                goToHome = goToHome
            )
        }
    }
}
