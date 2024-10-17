package com.shekharhandigol.data

import com.shekhargh.network.ApiService
import com.shekharhandigol.models.EmployeeResponse
import com.shekharhandigol.models.Resource
import javax.inject.Inject

interface HomeRepository {
    suspend fun getEmployeeList(): Resource<EmployeeResponse>
}

class HomeRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : HomeRepository {
    override suspend fun getEmployeeList(): Resource<EmployeeResponse> {
        return apiService.getEmployeeList()
    }
}