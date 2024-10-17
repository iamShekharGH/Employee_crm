package com.shekharhandigol.domain

import com.shekharhandigol.data.models.Employee
import com.shekharhandigol.data.models.Gender
import com.shekharhandigol.mapper.Mapper
import com.shekharhandigol.models.EmployeeResponse
import javax.inject.Inject

class EmployeeResponseMapper @Inject constructor() : Mapper<EmployeeResponse, List<Employee>> {
    override fun map(from: EmployeeResponse): List<Employee> {
        return from.data.map {
            Employee(
                eid = it.eid,
                name = it.name,
                gender = if (it.gender.equals(
                        "MALE",
                        ignoreCase = true
                    )
                ) Gender.MALE else Gender.FEMALE,
                title = it.title,
                photoUrl = it.photoUrl,
                presentToday = it.presentToday,
                salaryCredited = it.salaryCredited
            )
        }
    }
}