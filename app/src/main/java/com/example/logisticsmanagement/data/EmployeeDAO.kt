package com.example.logisticsmanagement.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmployeeDAO {
    @Query("select * from employee where jobNumber = :jobNumber")
    fun findByJobNumber(jobNumber: String): Employee?
}
