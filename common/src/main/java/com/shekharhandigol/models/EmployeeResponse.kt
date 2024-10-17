package com.shekharhandigol.models

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeResponse(
    val data: List<Employee>,
    val status: Int
) : Response {
    @Serializable
    data class Employee(
        val eid: Int,
        val gender: String,
        val name: String,
        val photoUrl: String,
        val presentToday: Boolean,
        val salaryCredited: Boolean,
        val title: String
    )
}