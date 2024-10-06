package com.shekharhandigol.data.models

data class UserInformation(
    val eid: Int,
    val name: String,
    val title: String,
    val email: String,
    val age: Int,
    val birthday: String,
    val photoUrl: String,
    val employeeGender: EmployeeGender,
    val presentToday: Boolean,
    val salaryCredited: Boolean
)

enum class EmployeeGender { Male, Female, Unrecognized }
