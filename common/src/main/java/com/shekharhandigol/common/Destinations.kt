package com.shekharhandigol.common

import kotlinx.serialization.Serializable

sealed class Destinations {

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

fun String.getNavDestinations(): Destinations {
    return when (this) {
        Destinations.SalarySummaryModule.getNavPath() -> Destinations.SalarySummaryModule
        Destinations.SalarySummary.getNavPath() -> Destinations.SalarySummary
        Destinations.Profile.getNavPath() -> Destinations.Profile
        Destinations.ProfileModule.getNavPath() -> Destinations.ProfileModule
        Destinations.HomeModule.getNavPath() -> Destinations.HomeModule
        Destinations.Home.getNavPath() -> Destinations.Home
        Destinations.AuthModule.getNavPath() -> Destinations.AuthModule
        Destinations.LoginScreen.getNavPath() -> Destinations.LoginScreen
        Destinations.Splash.getNavPath() -> Destinations.Splash
        Destinations.AttendanceSummaryModule.getNavPath() -> Destinations.AttendanceSummaryModule
        Destinations.AttendanceHome.getNavPath() -> Destinations.AttendanceHome
        else -> Destinations.HomeModule
    }

}

fun Destinations.getNavPath(): String {
    val ans = this.javaClass.name.replace("$", ".")
    return ans
}


/*
fun Destinations.getNavPath(): String {
    return "com.shekharhandigol.common.Destinations." + this.javaClass.simpleName

    TODO("Restructure this destinations class like this and make appropriate changes.")
    sealed class Destinations {

    sealed class Auth : Destinations() {
        @Serializable
        data object LoginScreen : Auth()
    }

    sealed class Home : Destinations() {
        @Serializable
        data object HomeModule : Home()
        @Serializable
        data object Home : Home() // Consider renaming to Dashboard or similar
    }

    sealed class Profile : Destinations() {
        @Serializable
        data object ProfileModule : Profile()
        @Serializable
        data object Profile : Profile()
    }

    sealed class Salary : Destinations() {
        @Serializable
        data object SalarySummaryModule : Salary()
        @Serializable
        data object SalarySummary : Salary()
    }

    sealed class Attendance : Destinations() {
        @Serializable
        data object AttendanceSummaryModule : Attendance()
        @Serializable
        data object AttendanceHome : Attendance()
    }

    @Serializable
    data object Splash : Destinations()

    @Serializable
    data class FriendsList(val name: String) : Destinations()
}
}*/
