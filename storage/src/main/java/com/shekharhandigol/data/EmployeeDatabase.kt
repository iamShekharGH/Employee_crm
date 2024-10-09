package com.shekharhandigol.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shekharhandigol.data.models.Employee
import com.shekharhandigol.data.models.EmployeeHolidays

@Database(entities = [Employee::class, EmployeeHolidays::class], version = 2)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDAO(): EmployeeDAO
    abstract fun holidaysDAO(): HolidaysDAO
}