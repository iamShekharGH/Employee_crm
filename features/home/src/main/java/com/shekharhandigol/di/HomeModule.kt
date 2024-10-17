package com.shekharhandigol.di


import com.shekhargh.network.ApiService
import com.shekharhandigol.data.HomeRepository
import com.shekharhandigol.data.HomeRepositoryImp
import com.shekharhandigol.data.models.Employee
import com.shekharhandigol.domain.EmployeeResponseMapper
import com.shekharhandigol.mapper.Mapper
import com.shekharhandigol.models.EmployeeResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun provideHomeRepository(apiService: ApiService): HomeRepository =
        HomeRepositoryImp(apiService)

    @Provides
    fun provideMapper(): Mapper<EmployeeResponse, List<Employee>> = EmployeeResponseMapper()
}