package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shekharhandigol.common.Destinations
import com.shekharhandigol.salarysummary.SalarySummaryScreen


fun NavGraphBuilder.salarySummaryNavGraph(navController: NavController, goToHome: () -> Unit) {

    navigation<Destinations.SalarySummaryModule>(startDestination = Destinations.SalarySummary) {
        composable<Destinations.SalarySummary> {
            SalarySummaryScreen()
        }
    }
}