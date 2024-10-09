package com.shekharhandigol.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "holidaysTable")
data class EmployeeHolidays(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val holidayName: String,
    val holidayDate: String,
    val holidayType: String,
    val holidayReason: String,
    val isHolidayTaken: Boolean,
)
