package com.shekharhandigol.datastore

import android.content.Context
import com.shekharhandigol.data.models.EmployeeGender
import com.shekharhandigol.data.models.UserInformation
import com.shekharhandigol.mapper.Mapper
import com.shekharhandigol.storage.AppUserInformation
import com.shekharhandigol.storage.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmployeeCRMSessionHandler @Inject constructor(
    private val context: Context,
    private val mapper: Mapper<AppUserInformation, UserInformation>,
    private val mapperUA: Mapper<UserInformation, AppUserInformation>
) : SessionHandler {
    override suspend fun saveSession(userInformation: UserInformation) {

        context.employeeDataStore.updateData {
            mapperUA.map(userInformation)
        }

        /* Am keeping this if there is a bug tomm.
        context.employeeDataStore.updateData {
            it.toBuilder().setEmployee(
                Employee.newBuilder().apply {
                    eid = userInformation.eid
                    name = userInformation.name
                    title = userInformation.title
                    photoUrl = userInformation.photoUrl
                    presentToday = userInformation.presentToday
                    salaryCredited = userInformation.salaryCredited
                    gender = when (userInformation.employeeGender) {
                        EmployeeGender.Male -> Employee.Gender.MALE
                        EmployeeGender.Female -> Employee.Gender.FEMALE
                        EmployeeGender.Unrecognized -> Employee.Gender.UNRECOGNIZED
                    }
                }
            )
                .setBirthday(userInformation.birthday)
                .setAge(userInformation.age)
                .setEmail(userInformation.email)
                .build()
        }*/
    }

    override suspend fun getUserInformation(): Flow<UserInformation> {
        return getSession().map {
            mapper.map(it)
        }
    }

    override suspend fun getSession(): Flow<AppUserInformation> {
        return context.employeeDataStore.data
    }

    override suspend fun clear() {
        context.employeeDataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}