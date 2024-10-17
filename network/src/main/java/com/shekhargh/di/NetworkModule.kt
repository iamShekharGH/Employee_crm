package com.shekhargh.di

import com.shekhargh.network.ApiService
import com.shekhargh.network.ApiServiceImp
import com.shekhargh.network.KtorClient
import com.shekharhandigol.datastore.SessionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKtorClient(sessionHandler: SessionHandler): HttpClient {
        return KtorClient(sessionHandler).build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient, sessionHandler: SessionHandler): ApiService {
        return ApiServiceImp(client, sessionHandler)
    }
}