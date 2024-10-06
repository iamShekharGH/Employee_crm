package com.shekharhandigol.mapper

import com.shekharhandigol.data.models.EmployeeGender
import com.shekharhandigol.data.models.UserInformation
import com.shekharhandigol.storage.AppUserInformation
import com.shekharhandigol.storage.Employee
import javax.inject.Inject

interface Mapper<F, T> {
    fun map(from: F): T
}

class FromAppToUserInformation @Inject constructor() : Mapper<AppUserInformation, UserInformation> {
    override fun map(from: AppUserInformation): UserInformation {
        return UserInformation(
            eid = from.employee.eid,
            name = from.employee.name,
            title = from.employee.title,
            email = from.email,
            age = from.age,
            birthday = from.birthday,
            photoUrl = from.employee.photoUrl,
            presentToday = from.employee.presentToday,
            salaryCredited = from.employee.salaryCredited,
            employeeGender = when (from.employee.gender) {
                Employee.Gender.MALE -> EmployeeGender.Male
                Employee.Gender.FEMALE -> EmployeeGender.Female
                Employee.Gender.UNRECOGNIZED -> EmployeeGender.Unrecognized
            }
        )
    }
}
