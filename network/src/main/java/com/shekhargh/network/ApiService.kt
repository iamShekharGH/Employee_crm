package com.shekhargh.network

import com.shekharhandigol.models.ErrorResponse
import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.LoginResponse
import com.shekharhandigol.models.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.io.IOException
import javax.inject.Inject

interface ApiService {
    suspend fun login(req: LoginRequest): Resource<LoginResponse>
    suspend fun getEmployeeList(): Flow<List<Employee>>
}

class ApiServiceImp @Inject constructor(private val client: HttpClient) : ApiService {

    override suspend fun login(req: LoginRequest): Resource<LoginResponse> {
        return try {
            val res: LoginResponse = client.post("/login") {
                contentType(ContentType.Application.Json)
                setBody(req)
            }.body()
            Resource.Success(res)

        } catch (e: Exception) {
            handleError(e)
        }
    }

    private suspend fun handleError(e: Exception): Resource<LoginResponse> {
        e.printStackTrace()
        when (e) {
            is RedirectResponseException -> {
                // 3xx - Redirection errors
                return Resource.Error(e.response.status.value, "Redirect error", e)
            }

            is ClientRequestException -> {
                // 4xx - Client error
                val res = e.response.body<ErrorResponse>()
                return Resource.Error(res.status, res.message, e)
            }

            is ServerResponseException -> {
                // 5xx - Server error
                return Resource.Error(e.response.status.value, "Server error", e)
            }

            is IOException -> {
                // Network issues (e.g., no internet connection)
                return Resource.Error(message = "Network error", cause = e)
            }

            else -> {
                // Any other unknown errors
                return Resource.Error(message = "Unknown error", cause = e)
            }
        }
    }

    override suspend fun getEmployeeList(): Flow<List<Employee>> {
        return client.get("/employees").body()
    }
}


data class Employee(val name: String, val age: Int)
/*data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)*/
