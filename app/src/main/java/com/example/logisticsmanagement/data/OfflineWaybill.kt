package com.example.logisticsmanagement.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offline_waybill")
data class OfflineWaybill(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String = "",  // 货物名称

    var count: Int = 0,  // 数量
    var src: String = "",  // 起点
    var dest: String = "",  // 终点
    var consignor: String = "",  // 发货人
    var consignorPhoneNumber: String = "",  // 发货人电话
    var consignee: String = "",  // 收货人
    var consigneePhoneNumber: String = "",  // 收货人电话
    var freightPaidByTheReceivingParty: Int = 0,  // 到付金额
    var freightPaidByConsignor: Int = 0,  // 预付金额
)