package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.attendancesummary.AttendanceSummaryScreen
import com.shekharhandigol.common.Destinations


fun NavGraphBuilder.attendanceSummaryNavGraph(navController: NavController, goToHome: () -> Unit) {
    navigation<Destinations.AttendanceSummaryModule>(startDestination = Destinations.AttendanceHome) {
        composable<Destinations.AttendanceHome> {
            AttendanceSummaryScreen(goToHome)
        }
    }
}

