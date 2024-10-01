package com.shekharhandigol.di

import android.content.Context
import androidx.room.Room
import com.shekharhandigol.data.EmployeeDAO
import com.shekharhandigol.data.EmployeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

const val DATABASE_FILE_NAME = "employees_database"

@Module
@InstallIn(SingletonComponent::class)
object EmployeeDatabaseModule {

    @Provides
    fun providesRoomInstance(
        @ApplicationContext context: Context
    ): EmployeeDatabase =
        Room.databaseBuilder(context, EmployeeDatabase::class.java, DATABASE_FILE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    fun provideDatabaseDao(
        db: EmployeeDatabase
    ): EmployeeDAO {
        return db.employeeDAO()
    }

}