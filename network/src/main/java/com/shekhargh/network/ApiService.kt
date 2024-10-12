package com.shekhargh.network

import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ApiService {
    suspend fun login(req: LoginRequest): Flow<LoginResponse>
    suspend fun getEmployeeList(): Flow<List<Employee>>
}

class ApiServiceImp @Inject constructor(private val client: HttpClient) : ApiService {

    override suspend fun login(req: LoginRequest): Flow<LoginResponse> {
        return flow {
            emit(
                client.post("$/login") {
                    contentType(ContentType.Application.Json)
                    setBody(req)
                }.body()
            )
        }
    }

    override suspend fun getEmployeeList(): Flow<List<Employee>> {
        return client.get("/employees").body()
    }
}


data class Employee(val name: String, val age: Int)
/*data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)*/
