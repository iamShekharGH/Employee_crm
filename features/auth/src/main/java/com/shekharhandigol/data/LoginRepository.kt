package com.shekharhandigol.data

import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Flow<LoginResponse>
}