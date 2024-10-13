package com.shekharhandigol.data

import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.LoginResponse
import com.shekharhandigol.models.Resource

interface LoginRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse>
}