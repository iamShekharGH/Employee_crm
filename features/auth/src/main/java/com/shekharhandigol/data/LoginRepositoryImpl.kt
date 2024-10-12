package com.shekharhandigol.data

import com.shekhargh.network.ApiService
import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.LoginResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LoginRepository {

    override suspend fun loginUser(loginRequest: LoginRequest): Flow<LoginResponse> {
        return apiService.login(loginRequest)
    }
}