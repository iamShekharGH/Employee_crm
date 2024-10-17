package com.shekharhandigol.domain

import com.shekharhandigol.data.HomeRepository
import com.shekharhandigol.data.models.Employee
import com.shekharhandigol.mapper.Mapper
import com.shekharhandigol.models.EmployeeResponse
import com.shekharhandigol.models.Resource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val employeeRepository: EmployeeRepository,
    private val mapper: Mapper<EmployeeResponse, List<Employee>>
) {
    suspend fun invoke(): Resource<List<Employee>> {
        return when (val employees = repository.getEmployeeList()) {
            is Resource.Success -> {
                val mappedData = mapper.map(employees.data)
                employeeRepository.insertEmployees(mappedData)
                Resource.Success(mappedData)
            }

            is Resource.Error -> {
//                Resource.Error(status = employees.status, message = employees.message)

                Resource.Success(employeeRepository.getAllEmployees().first())
            }

            is Resource.Loading -> {
                Resource.Loading()
            }
        }
    }
}

