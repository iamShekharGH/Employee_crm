package com.shekharhandigol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shekharhandigol.attendancesummary.AttendanceSummaryScreen
import kotlinx.serialization.Serializable

sealed class AttendanceSummaryDestinations {
    @Serializable
    data object AttendanceSummaryModule : AttendanceSummaryDestinations()

    @Serializable
    data object AttendanceHome : AttendanceSummaryDestinations()
}

fun NavGraphBuilder.attendanceSummaryNavGraph(navController: NavController) {
    navigation<AttendanceSummaryDestinations.AttendanceSummaryModule>(startDestination = AttendanceSummaryDestinations.AttendanceHome) {
        composable<AttendanceSummaryDestinations.AttendanceHome> {
            AttendanceSummaryScreen()
        }
    }
}

