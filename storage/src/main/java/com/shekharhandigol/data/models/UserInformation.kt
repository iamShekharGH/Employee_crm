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

fun UserInformation.isValid(): Boolean {
    return name.isNotEmpty() && title.isNotEmpty() && email.isNotEmpty() && age > 0
}
fun UserInformation.isPartiallyValid(): Boolean {
    return name.isNotEmpty() || title.isNotEmpty() || email.isNotEmpty() || age > 0
}


enum class EmployeeGender { Male, Female, Unrecognized }
