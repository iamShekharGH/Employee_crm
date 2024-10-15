package com.shekharhandigol.mapper

import com.shekharhandigol.models.EmployeeGender
import com.shekharhandigol.models.UserInformation
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
            salary = from.salary,
            employeeGender = when (from.employee.gender) {
                Employee.Gender.MALE -> EmployeeGender.Male
                Employee.Gender.FEMALE -> EmployeeGender.Female
                Employee.Gender.UNRECOGNIZED -> EmployeeGender.Unrecognized
            }
        )
    }
}
class FromUserInformationToApp @Inject constructor() : Mapper<UserInformation, AppUserInformation> {

    override fun map(from: UserInformation): AppUserInformation {
        return AppUserInformation.getDefaultInstance().toBuilder().apply {
            employee = Employee.getDefaultInstance().toBuilder().apply {
                eid = from.eid
                name = from.name
                title = from.title
                photoUrl = from.photoUrl
                presentToday = from.presentToday
                salaryCredited = from.salaryCredited
                salary = from.salary
                gender = when (from.employeeGender) {
                    EmployeeGender.Male -> Employee.Gender.MALE
                    EmployeeGender.Female -> Employee.Gender.FEMALE
                    EmployeeGender.Unrecognized -> Employee.Gender.UNRECOGNIZED
                }
                email = from.email
                birthday = from.birthday
                age = from.age

            }.build()
            age = from.age
            birthday = from.birthday
            email = from.email
        }.build()
    }
}
