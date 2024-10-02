package com.shekharhandigol.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "employeesTable")
data class Employee(
    @PrimaryKey(autoGenerate = true) val eid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "gender") val gender: Gender,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "photoUrl") val photoUrl: String,
    @ColumnInfo(name = "presentToday") val presentToday: Boolean,
    @ColumnInfo(name = "salaryCredited") val salaryCredited: Boolean,
)

enum class Gender { MALE, FEMALE }