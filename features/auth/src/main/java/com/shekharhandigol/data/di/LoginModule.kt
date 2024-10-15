package com.shekharhandigol.data.di

import com.shekhargh.network.ApiService
import com.shekharhandigol.data.LoginRepository
import com.shekharhandigol.data.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideLoginRepository(apiService: ApiService): LoginRepository =
        LoginRepositoryImpl(apiService)
}