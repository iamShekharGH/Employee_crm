package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shekharhandigol.salarysummary.SalarySummaryScreen
import kotlinx.serialization.Serializable

sealed class SalarySummaryDestinations {

    @Serializable
    data object SalarySummaryModule : SalarySummaryDestinations()

    @Serializable
    data object SalarySummary : SalarySummaryDestinations()

}


fun NavGraphBuilder.salarySummaryNavGraph(navController: NavController) {

    navigation<SalarySummaryDestinations.SalarySummaryModule>(startDestination = SalarySummaryDestinations.SalarySummary) {
        composable<SalarySummaryDestinations.SalarySummary> {
            SalarySummaryScreen()
        }
    }
}