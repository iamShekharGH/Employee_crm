package com.shekharhandigol.crm

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shekharhandigol.attendanceSummaryNavGraph
import com.shekharhandigol.auth.AuthDestinations
import com.shekharhandigol.auth.authNavGraph
import com.shekharhandigol.homeNavigationGraph
import com.shekharhandigol.profileNavGraph
import com.shekharhandigol.salarySummaryNavGraph
import kotlinx.serialization.Serializable


sealed class HomeDestinations {

    @Serializable
    data object Home : HomeDestinations()

    @Serializable
    data class FriendsList(val name: String) : HomeDestinations()
}

@Composable
fun EmployeeCrmAppNavHost(navController: NavHostController, onSignInClick: () -> Unit) {

    NavHost(
        navController = navController,
//        startDestination = com.shekharhandigol.auth.Destinations.AuthModule
//        startDestination = com.shekharhandigol.ProfileDestinations.ProfileModule
//        startDestination = SalarySummaryDestinations.SalarySummaryModule
        startDestination = AuthDestinations.AuthModule
    ) {

        authNavGraph(navController = navController, onSignInClick = onSignInClick)
        profileNavGraph(navController = navController)
        homeNavigationGraph(navController = navController)
        attendanceSummaryNavGraph(navController = navController)
        salarySummaryNavGraph(navController = navController)


        composable<HomeDestinations.Home> {

            HomeScreen()
        }
    }
}
