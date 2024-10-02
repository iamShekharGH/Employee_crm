package com.shekharhandigol.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shekharhandigol.data.models.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDAO(): EmployeeDAO
}