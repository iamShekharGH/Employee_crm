package com.shekharhandigol.data

import com.shekhargh.network.ApiService
import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.LoginResponse
import com.shekharhandigol.models.Resource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LoginRepository {

    override suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse> {
        return apiService.login(loginRequest)
    }
}