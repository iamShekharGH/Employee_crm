package com.shekharhandigol

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.shekharhandigol.models.Employee


@Dao
interface EmployeeDAO {
    @Query("SELECT * FROM employeesTable")
    fun getAll(): List<Employee>

    @Query("SELECT * FROM employeesTable WHERE name IN (:employeeIds)")
    fun loadAllByIds(employeeIds: IntArray): List<Employee>

    @Query("SELECT * FROM employeesTable WHERE name LIKE :first LIMIT 1")
    fun findByName(first: String, last: String): Employee

    @Insert
    fun insertAll(vararg employees: Employee)

    @Delete
    fun delete(employee: Employee)
}