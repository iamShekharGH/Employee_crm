package com.shekharhandigol.domain

import com.shekharhandigol.data.EmployeeDAO
import com.shekharhandigol.data.models.Employee
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(
    private val dao: EmployeeDAO
) {

    suspend fun insertNewEmployee(e: Employee) {
        dao.insertEmployee(e)
    }
    suspend fun insertEmployees(e: List<Employee>) {
        dao.insertAll(*e.toTypedArray())
    }

    fun getAllEmployees(): Flow<List<Employee>> {
        return dao.getAll()
    }

    suspend fun deleteEmployee(e: Employee) {
        dao.delete(e)
    }

    suspend fun deleteEmployeewithId(eid: Int) {
        dao.deleteEmployeeWithId(eid)
    }

    fun getEmployeeOfName(name: String): Flow<List<Employee>> {
        return dao.findByName(name)
    }

    suspend fun insertDummy() {
        dao.insertRandomInfo()
    }
}