package com.shekharhandigol.common

import kotlinx.serialization.Serializable

//interface Destinations
sealed class  Destinations {

    @Serializable
    data object SalarySummaryModule : Destinations()

    @Serializable
    data object SalarySummary : Destinations()
    @Serializable
    data object Profile : Destinations()

    @Serializable
    data object ProfileModule : Destinations()

    @Serializable
    data object HomeModule : Destinations()

    @Serializable
    data object Home : Destinations()

    @Serializable
    data object AuthModule : Destinations()

    @Serializable
    data object LoginScreen : Destinations()

    @Serializable
    data object Splash : Destinations()

    @Serializable
    data object AttendanceSummaryModule : Destinations()

    @Serializable
    data object AttendanceHome : Destinations()


    @Serializable
    data class FriendsList(val name: String) : Destinations()


}
