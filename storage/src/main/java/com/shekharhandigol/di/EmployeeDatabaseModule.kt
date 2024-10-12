package com.shekharhandigol.di

import android.content.Context
import androidx.room.Room
import com.shekharhandigol.data.EmployeeDAO
import com.shekharhandigol.data.EmployeeDatabase
import com.shekharhandigol.data.HolidaysDAO
import com.shekharhandigol.models.UserInformation
import com.shekharhandigol.datastore.EmployeeCRMSessionHandler
import com.shekharhandigol.datastore.SessionHandler
import com.shekharhandigol.mapper.FromAppToUserInformation
import com.shekharhandigol.mapper.FromUserInformationToApp
import com.shekharhandigol.mapper.Mapper
import com.shekharhandigol.storage.AppUserInformation
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

    @Provides
    fun provideHolidaysDao(
        db: EmployeeDatabase
    ): HolidaysDAO {
        return db.holidaysDAO()
    }

    @Provides
    fun provideMapperAtoU(): Mapper<AppUserInformation, UserInformation> =
        FromAppToUserInformation()

    @Provides
    fun provideMapperUtoA(): Mapper<UserInformation, AppUserInformation> =
        FromUserInformationToApp()


    @Provides
    fun provideSessionHandler(
        @ApplicationContext context: Context,
        mapper: Mapper<AppUserInformation, UserInformation>,
        mapperUA: Mapper<UserInformation, AppUserInformation>,
    ): SessionHandler =
        EmployeeCRMSessionHandler(
            context = context,
            mapper = mapper,
            mapperUA = mapperUA
        )

}