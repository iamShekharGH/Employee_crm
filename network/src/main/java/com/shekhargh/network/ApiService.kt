package com.shekhargh.network

import com.shekharhandigol.models.EmployeeResponse
import com.shekharhandigol.models.ErrorResponse
import com.shekharhandigol.models.LoginRequest
import com.shekharhandigol.models.LoginResponse
import com.shekharhandigol.models.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.io.IOException
import timber.log.Timber
import javax.inject.Inject

interface ApiService {
    suspend fun login(req: LoginRequest): Resource<LoginResponse>
    suspend fun getEmployeeList(): Resource<EmployeeResponse>
}

class ApiServiceImp @Inject constructor(private val client: HttpClient) : ApiService {

    private suspend inline fun <reified T> makeApiRequest(request: () -> T): Resource<T> =
        runCatching {
            request()
        }.fold(
            onSuccess = { Resource.Success(it) },
            onFailure = { handleError(it) }
        )

    override suspend fun login(req: LoginRequest): Resource<LoginResponse> = makeApiRequest {
        client.post("/login") {
            contentType(ContentType.Application.Json)
            setBody(req)
        }.body()
    }

    override suspend fun getEmployeeList(): Resource<EmployeeResponse> = makeApiRequest {
        client.get("/employees").body()
    }

    private suspend fun <T> handleError(e: Throwable): Resource<T> {
        Timber.e(e, "API error")
        return when (e) {
            // 3xx - Redirection errors
            is RedirectResponseException -> {

                Resource.Error(
                    e.response.status.value,
                    "Redirect error: ${e.response.status.description}",
                    e
                )
            }
            // 4xx - Client error
            is ClientRequestException -> {

                val errorResponse = e.response.body<ErrorResponse>()
                Resource.Error(
                    errorResponse.status,
                    errorResponse.message,
                    e
                )
            }
            // 5xx - Server error
            is ServerResponseException -> {
                Resource.Error(
                    e.response.status.value,
                    "Server error: ${e.response.status.description}",
                    e
                )
            }

            is HttpRequestTimeoutException -> {
                Resource.Error(message = "Request timed out", cause = e)
            }

            is IOException -> {
                Resource.Error(message = "Network error", cause = e)
            }

            else -> {
                Resource.Error(message = "Unknown error", cause = e)
            }
        }
    }
}
