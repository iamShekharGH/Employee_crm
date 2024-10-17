package com.shekharhandigol.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shekharhandigol.data.models.Employee
import kotlinx.coroutines.flow.Flow


@Dao
interface EmployeeDAO {
    @Query("SELECT * FROM employeesTable")
    fun getAll(): Flow<List<Employee>>

    @Query("SELECT * FROM employeesTable WHERE name IN (:employeeIds)")
    fun loadAllByIds(employeeIds: IntArray): Flow<List<Employee>>

    @Query("SELECT * FROM employeesTable WHERE name LIKE :name LIMIT 1")
    fun findFirstByName(name: String): Employee

    @Query("SELECT * FROM employeesTable WHERE name LIKE :name")
    fun findByName(name: String): Flow<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg employees: Employee)

    @Insert
    suspend fun insertEmployee(employee: Employee)

    @Delete
    suspend fun delete(employee: Employee)

    @Query("DELETE FROM employeesTable WHERE eid = :eid")
    suspend fun deleteEmployeeWithId(eid: Int)

    @Query("""
        INSERT INTO employeesTable (name, gender, title, photoUrl, presentToday, salaryCredited)
        VALUES
        ('Rahul Sharma', 'MALE', 'Software Engineer', 'https://example.com/profile1.jpg', 1, 1),
        ('Aisha Khan', 'FEMALE', 'Project Manager', 'https://example.com/profile2.jpg', 0, 1),
        ('Vivek Patel', 'MALE', 'UI/UX Designer', 'https://example.com/profile3.jpg', 1, 0),
        ('Priya Singh', 'FEMALE', 'Data Analyst', 'https://example.com/profile4.jpg', 0, 1),
        ('Rajesh Kumar', 'MALE', 'Marketing Specialist', 'https://example.com/profile5.jpg', 1, 0),
        ('Neha Desai', 'FEMALE', 'HR Manager', 'https://example.com/profile6.jpg', 1, 1),
        ('Amit Gupta', 'MALE', 'Data Scientist', 'https://example.com/profile7.jpg', 0, 1),
        ('Riya Roy', 'FEMALE', 'UI/UX Designer', 'https://example.com/profile8.jpg', 1, 0),
        ('Vikram Singh', 'MALE', 'Software Developer', 'https://example.com/profile9.jpg', 0, 1),
        ('Anjali Patel', 'FEMALE', 'Product Manager', 'https://example.com/profile10.jpg', 1, 0),
        ('Rohit Sharma', 'MALE', 'Sales Manager', 'https://example.com/profile11.jpg', 0, 1),
        ('Amita Rao', 'FEMALE', 'Finance Analyst', 'https://example.com/profile12.jpg', 1, 0),
        ('Karan Malhotra', 'MALE', 'Content Writer', 'https://example.com/profile13.jpg', 0, 1),
        ('Nisha Gupta', 'FEMALE', 'Graphic Designer', 'https://example.com/profile14.jpg', 1, 0),
        ('Rahul Verma', 'MALE', 'Business Analyst', 'https://example.com/profile15.jpg', 0, 1),
        ('Meera Nair', 'FEMALE', 'HR Consultant', 'https://example.com/profile16.jpg', 1, 1),
        ('Nikhil Jain', 'MALE', 'Data Engineer', 'https://example.com/profile17.jpg', 0, 1),
        ('Ananya Banerjee', 'FEMALE', 'UI/UX Designer', 'https://example.com/profile18.jpg', 1, 0),
        ('Siddharth Roy', 'MALE', 'Software Architect', 'https://example.com/profile19.jpg', 0, 1),
        ('Ayesha Khan', 'FEMALE', 'Product Owner', 'https://example.com/profile20.jpg', 1, 0),
        ('Karan Singh', 'MALE', 'Sales Executive', 'https://example.com/profile21.jpg', 0, 1),
        ('Amita Mehta', 'FEMALE', 'Financial Analyst', 'https://example.com/profile22.jpg', 1, 0),
        ('Vivek Sharma', 'MALE', 'Content Strategist', 'https://example.com/profile23.jpg', 0, 1),
        ('Nisha Patel', 'FEMALE', 'Graphic Designer', 'https://example.com/profile24.jpg', 1, 0),
        ('Rahul Gupta', 'MALE', 'Business Development Manager', 'https://example.com/profile25.jpg', 0, 1),
        ('Meera Rao', 'FEMALE', 'HR Consultant', 'https://example.com/profile26.jpg', 1, 1),
        ('Nikhil Verma', 'MALE', 'Data Scientist', 'https://example.com/profile27.jpg', 0, 1),
        ('Ananya Gupta', 'FEMALE', 'UI/UX Designer', 'https://example.com/profile28.jpg', 1, 0),
        ('Siddharth Kumar', 'MALE', 'Software Architect', 'https://example.com/profile29.jpg', 0, 1),
        ('Ayesha Singh', 'FEMALE', 'Product Owner', 'https://example.com/profile30.jpg', 1, 0),
        ('Karan Patel', 'MALE', 'Sales Executive', 'https://example.com/profile31.jpg', 0, 1),
        ('Amita Verma', 'FEMALE', 'Financial Analyst', 'https://example.com/profile32.jpg', 1, 0),
        ('Vivek Gupta', 'MALE', 'Content Strategist', 'https://example.com/profile33.jpg', 0, 1),
        ('Nisha Sharma', 'FEMALE', 'Graphic Designer', 'https://example.com/profile34.jpg', 1, 0),
        ('Rahul Rao', 'MALE', 'Business Development Manager', 'https://example.com/profile35.jpg', 0, 1),
        ('Meera Verma', 'FEMALE', 'HR Consultant', 'https://example.com/profile36.jpg', 1, 1),
        ('Nikhil Gupta', 'MALE', 'Data Scientist', 'https://example.com/profile37.jpg', 0, 1),
        ('Ananya Sharma', 'FEMALE', 'UI/UX Designer', 'https://example.com/profile38.jpg', 1, 0),
        ('Siddharth Patel', 'MALE', 'Software Architect', 'https://example.com/profile39.jpg', 0, 1),
        ('Ayesha Verma', 'FEMALE', 'Product Owner', 'https://example.com/profile40.jpg', 1, 0),
        ('Karan Gupta', 'MALE', 'Sales Executive', 'https://example.com/profile41.jpg', 0, 1),
        ('Amita Sharma', 'FEMALE', 'Financial Analyst', 'https://example.com/profile42.jpg', 1, 0),
        ('Vivek Patel', 'MALE', 'Content Strategist', 'https://example.com/profile43.jpg', 0, 1),
        ('Nisha Gupta', 'FEMALE', 'Graphic Designer', 'https://example.com/profile44.jpg', 1, 0),
        ('Rahul Sharma', 'MALE', 'Business Development Manager', 'https://example.com/profile45.jpg', 0, 1),
        ('Aaditya Singh', 'MALE', 'Software Developer', 'https://example.com/profile46.jpg', 1, 1),
        ('Anika Roy', 'FEMALE', 'Product Manager', 'https://example.com/profile47.jpg', 0, 1),
        ('Bhavya Patel', 'FEMALE', 'UI/UX Designer', 'https://example.com/profile48.jpg', 1, 0),
        ('Chinmay Sharma', 'MALE', 'Data Scientist', 'https://example.com/profile49.jpg', 0, 1),
        ('Dhruv Gupta', 'MALE', 'Software Architect', 'https://example.com/profile50.jpg', 1, 0),
        ('Eesha Verma', 'FEMALE', 'HR Manager', 'https://example.com/profile51.jpg', 0, 1),
        ('Firoz Khan', 'MALE', 'Sales Executive', 'https://example.com/profile52.jpg', 1, 0),
        ('Garima Nair', 'FEMALE', 'Financial Analyst', 'https://example.com/profile53.jpg', 0, 1),
        ('Hitesh Singh', 'MALE', 'Content Strategist', 'https://example.com/profile54.jpg', 1, 0),
        ('Ishan Patel', 'MALE', 'Graphic Designer', 'https://example.com/profile55.jpg', 0, 1),
        ('Janhavi Verma', 'FEMALE', 'Business Development Manager', 'https://example.com/profile56.jpg', 1, 0),
        ('Kailash Gupta', 'MALE', 'Software Developer', 'https://example.com/profile57.jpg', 0, 1),
        ('Lakshmi Nair', 'FEMALE', 'Product Manager', 'https://example.com/profile58.jpg', 1, 0),
        ('Madhav Sharma', 'MALE', 'UI/UX Designer', 'https://example.com/profile59.jpg', 0, 1),
        ('Nisha Patel', 'FEMALE', 'Data Scientist', 'https://example.com/profile60.jpg', 1, 0),
        ('Ojas Verma', 'MALE', 'Software Architect', 'https://example.com/profile61.jpg', 0, 1),
        ('Priyanka Gupta', 'FEMALE', 'HR Manager', 'https://example.com/profile62.jpg', 1, 0),
        ('Rajeev Singh', 'MALE', 'Sales Executive', 'https://example.com/profile63.jpg', 0, 1),
        ('Sarika Nair', 'FEMALE', 'Financial Analyst', 'https://example.com/profile64.jpg', 1, 0),
        ('Tushar Sharma', 'MALE', 'Content Strategist', 'https://example.com/profile65.jpg', 0, 1),
        ('Uma Patel', 'FEMALE', 'Graphic Designer', 'https://example.com/profile66.jpg', 1, 0),
        ('Vikram Verma', 'MALE', 'Software Developer', 'https://example.com/profile67.jpg', 0, 1),
        ('Yash Gupta', 'MALE', 'Product Manager', 'https://example.com/profile68.jpg', 1, 0),
        ('Zarina Nair', 'FEMALE', 'UI/UX Designer', 'https://example.com/profile69.jpg', 0, 1),
        ('Aakash Sharma', 'MALE', 'Data Scientist', 'https://example.com/profile70.jpg', 1, 0),
        ('Bhavya Singh', 'FEMALE', 'Software Architect', 'https://example.com/profile71.jpg', 0, 1),
        ('Chandrika Patel', 'FEMALE', 'HR Manager', 'https://example.com/profile72.jpg', 1, 0),
        ('Dhiraj Verma', 'MALE', 'Sales Executive', 'https://example.com/profile73.jpg', 0, 1),
        ('Eesha Gupta', 'FEMALE', 'Financial Analyst', 'https://example.com/profile74.jpg', 1, 0),
        ('Firoz Sharma', 'MALE', 'Content Strategist', 'https://example.com/profile75.jpg', 0, 1);
    """)
    suspend fun insertRandomInfo()
}