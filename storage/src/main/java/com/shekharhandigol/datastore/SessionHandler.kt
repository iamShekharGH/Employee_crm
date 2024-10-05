package com.shekharhandigol.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.shekharhandigol.storage.AppUserInformation
import kotlinx.coroutines.flow.Flow

val Context.employeeDataStore: DataStore<AppUserInformation> by dataStore(
    fileName = "settings.pb",
    serializer = AppUserInformationSerializer
)

interface SessionHandler {
    suspend fun saveSession(userInformation: com.shekharhandigol.data.models.UserInformation)
    suspend fun getSession(): Flow<AppUserInformation>
    suspend fun clear()
}

