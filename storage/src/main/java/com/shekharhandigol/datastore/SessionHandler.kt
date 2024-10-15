package com.shekharhandigol.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.shekharhandigol.models.UserInformation
import com.shekharhandigol.storage.AppUserInformation
import com.shekharhandigol.storage.AuthToken
import kotlinx.coroutines.flow.Flow

val Context.employeeDataStore: DataStore<AppUserInformation> by dataStore(
    fileName = "settings.pb",
    serializer = AppUserInformationSerializer
)
val Context.authTokenDataStore: DataStore<AuthToken> by dataStore(
    fileName = "authToken.pb",
    serializer = AuthTokenSerializer
)

interface SessionHandler {
    suspend fun saveSession(userInformation: UserInformation)
    suspend fun getSession(): Flow<AppUserInformation>
    suspend fun clear()
    suspend fun getUserInformation(): Flow<UserInformation>
    suspend fun getAuthToken(): Flow<String?>
    suspend fun setAuthToken(authToken: String)
}

