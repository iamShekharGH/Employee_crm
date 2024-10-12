package com.shekhargh.network

import android.util.Log
import com.shekharhandigol.datastore.SessionHandler
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import javax.inject.Inject


class KtorClient @Inject constructor(
    private val sessionHandler: SessionHandler
) {
    companion object {
        const val BASE_URL = "http://127.0.0.1:8080/"
    }

    fun build(): HttpClient {
        return HttpClient(CIO) {

            expectSuccess = true
            configureHttpClient(this)
        }
    }

    private fun configureHttpClient(httpClient: HttpClientConfig<CIOEngineConfig>) {
        httpClient.apply {

            install(HttpTimeout) {
                socketTimeoutMillis = 30000
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 30000
            }

            install(Logging) {
                logger = KtorLogger()
               // level = LogLevel.ALL  causing crash!!!
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            defaultRequest {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }

            install(ContentNegotiation) {

                Json {
                    prettyPrint = true
                    isLenient = true
                    useAlternativeNames = true
                    ignoreUnknownKeys = true
                    encodeDefaults = false
                }
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val token = runBlocking {
                            sessionHandler.getAuthToken().first()
                        }

                        BearerTokens(token ?: "", "")
                    }
                }
            }


        }
    }
}

class KtorLogger : Logger {
    override fun log(message: String) {
        Log.v("Ktor", message)
    }
}

