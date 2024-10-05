package com.shekharhandigol.datastore

import android.content.Context
import com.shekharhandigol.storage.AppUserInformation
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployeeCRMSessionHandler @Inject constructor(
    @ApplicationContext private val context: Context,
) : SessionHandler {
    override suspend fun saveSession(appUserInformation: AppUserInformation) {
        context.employeeDataStore.updateData {
            it.toBuilder()
                .setEmployee(appUserInformation.employee)
                .setBirthday(appUserInformation.birthday)
                .setAge(appUserInformation.age)
                .setEmail(appUserInformation.email)
                .build()
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