package com.shekharhandigol.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.shekharhandigol.data.models.EmployeeHolidays
import kotlinx.coroutines.flow.Flow

@Dao
interface HolidaysDAO {

    @Query("SELECT * FROM holidaysTable")
    fun getAll(): Flow<List<EmployeeHolidays>>

    @Insert
    suspend fun insertAll(vararg holidays: EmployeeHolidays)

    @Insert
    suspend fun insertHoliday(holiday: EmployeeHolidays)

    @Delete
    suspend fun delete(holiday: EmployeeHolidays)

    @Query("DELETE FROM holidaysTable")
    suspend fun deleteAllHolidays()

    @Query("DELETE FROM holidaysTable WHERE holidayDate = :hDate")
    suspend fun deleteHoliday(hDate: String)

    @Query(
        """
        INSERT INTO holidaysTable (holidayName, holidayDate, holidayType, holidayReason, isHolidayTaken)
            VALUES
            ('Republic Day', '26-01-2024', 'National', 'Celebrates Indias independence', 1),
            ('Good Friday', '29-03-2024', 'Religious', 'Christian holiday commemorating the crucifixion of Jesus', 0),
            ('Chaitra Sukladi /Gudi Padava / Ugadi Cheti Chand', '09-04-2024', 'Religious', 'Hindu new year', 0),
            ('Id-ul-Fitr', '11-04-2024', 'Religious', 'Muslim festival marking the end of Ramadan', 1),
            ('Mahavir Jayanthi', '21-04-2024', 'Religious', 'Birthday of Jain Tirthankara Mahavir', 0),
            ('Buddha Purnima', '23-05-2024', 'Religious', 'Birthday of Buddha', 0),
            ('Id-ul-Zuha (Bakrid)', '17-06-2024', 'Religious', 'Muslim festival celebrating the sacrifice of Ibrahim', 0),
            ('Muharram', '17-07-2024', 'Religious', 'Islamic month of mourning', 1),
            ('Independence Day', '15-08-2024', 'National', 'Celebrates Indias independence', 0),
            ('Ganesh Chaturthi', '07-09-2024', 'Religious', 'Hindu festival celebrating Lord Ganesha', 0),
            ('Milad Un Nabi or ID-E-Milad (Birthday of Prophet Mohammad)', '16-09-2024', 'Religious', 'Birthday of Prophet Muhammad', 1),
            ('Mahatma Gandhis Birthday', '02-10-2024', 'National', 'Birthday of Mahatma Gandhi', 0),
            ('Dussehra [Mahanavarni]', '11-10-2024', 'Religious', 'Hindu festival celebrating the victory of good over evil', 0),
            ('Dussehra (Vijay Dashmi)', '12-10-2024', 'Religious', 'Hindu festival celebrating the victory of good over evil', 1),
            ('Diwali (Deepavali)', '31-10-2024', 'Religious', 'Hindu festival of lights', 1),
            ('Guru Nanaks Birthday', '15-11-2024', 'Religious', 'Birthday of Guru Nanak, founder of Sikhism', 0),
            ('Christmas Day', '25-12-2024', 'Religious', 'Christian celebration of the birth of Jesus', 0);
    """
    )
    suspend fun insertNationalHolidays()

}