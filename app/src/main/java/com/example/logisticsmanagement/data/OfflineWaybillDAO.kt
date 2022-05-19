package com.example.logisticsmanagement.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OfflineWaybillDAO {
    @Query("select * from offline_waybill")
    fun getAll(): List<OfflineWaybill>

    @Insert
    fun insert(offlineWaybill: OfflineWaybill)
}