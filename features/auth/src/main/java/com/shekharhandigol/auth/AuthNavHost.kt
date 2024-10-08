package com.shekharhandigol.auth

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.auth.login.LoginScreen
import com.shekharhandigol.auth.login.LoginScreenViewModel
import com.shekharhandigol.auth.splash.SplashScreen
import com.shekharhandigol.common.Destinations


fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    onSignInClick: () -> Unit,
    goToHome: () -> Unit
) {

    navigation<Destinations.AuthModule>(startDestination = Destinations.Splash) {
        composable<Destinations.Splash> { backStackEntry ->
            val aViewModel: LoginScreenViewModel = hiltViewModel(backStackEntry)

            SplashScreen(
                { navController.navigate(Destinations.LoginScreen) },
                goToHome,
                viewModel = aViewModel
            )
        }
        composable<Destinations.LoginScreen> { backStackEntry ->
            val aViewModel: LoginScreenViewModel =
                if (navController.previousBackStackEntry != null) hiltViewModel(
                    navController.previousBackStackEntry!!
                ) else hiltViewModel()

            LoginScreen(
                onSignInClick = onSignInClick,
                goToHome = goToHome,
                viewModel = aViewModel
            )
        }
    }
}
