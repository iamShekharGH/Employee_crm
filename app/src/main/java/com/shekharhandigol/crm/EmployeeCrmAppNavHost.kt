package com.shekharhandigol.crm

import HomeScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shekharhandigol.attendanceSummaryNavGraph
import com.shekharhandigol.auth.authNavGraph
import com.shekharhandigol.common.Destinations
import com.shekharhandigol.homeNavigationGraph
import com.shekharhandigol.profileNavGraph
import com.shekharhandigol.salarySummaryNavGraph


fun travelToDestination(
    navController: NavHostController,
    destination: Destinations
): () -> Unit {

    return {
        navController.navigate(destination)
    }
}

infix fun NavHostController.to(d: Destinations) {
    run { this.navigate(d) }
}

@Composable
fun EmployeeCrmAppNavHost(
    go: NavHostController,
    openDrawer: () -> suspend () -> Unit,
    onSignInClick: () -> Unit
) {

    NavHost(
        navController = go,
        startDestination = Destinations.AuthModule
    ) {

        authNavGraph(
            navController = go,
            onSignInClick = onSignInClick,
            goToHome = {

                Log.d("AuthNavGraph", buildString {
                    append("goToHome")
                    append("go.graph.id = ${go.graph.id}")
                })
//                TODO("uncomment when check login is working")
                go.popBackStack(go.graph.id, inclusive = true)
                go to Destinations.Home
            },
        )
        profileNavGraph(
            navController = go,
            goToHome = { go.popBackStack() },
            gotoSplash = {
                go.popBackStack()
                go to Destinations.Splash
            }
        )
        homeNavigationGraph(
            navController = go,
            goToProfile = { go to Destinations.Profile },
            openDrawer = openDrawer
        )
        attendanceSummaryNavGraph(
            navController = go,
            goToHome = {
                go.popBackStack(go.graph.id, inclusive = true)
                go to Destinations.Home
            }
        )
        salarySummaryNavGraph(
            navController = go,
            goToHome = {
                go.popBackStack(go.graph.id, inclusive = true)
                go to Destinations.Home
            }
        )


        composable<Destinations.Home> {

            HomeScreen()
        }
    }
}
