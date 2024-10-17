package com.shekharhandigol.domain

import com.shekharhandigol.data.LoginRepository
import com.shekharhandigol.datastore.SessionHandler
import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.LoginResponse
import com.shekharhandigol.models.Resource
import timber.log.Timber
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: LoginRepository,
    private val dataStore: SessionHandler

) {

    suspend fun invoke(username: String, password: String): Resource<LoginResponse> {
        val res = repo.loginUser(
            LoginRequest(username, password)
        )
        if (res is Resource.Success) {
            dataStore.saveSession(res.data.data)
            dataStore.setAuthToken(res.data.authKey)
        }

        Timber.d("LoginResult: $res")

        return res
    }

}