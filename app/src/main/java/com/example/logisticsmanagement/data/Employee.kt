package com.example.logisticsmanagement.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["jobNumber"], unique = true)])
data class Employee(
    @PrimaryKey val id: Int,

    val name: String,

    // 用于登录的账号，要求是唯一字段
    val jobNumber: String,

    val password: String,

    val department: String?,

    val telephone: String?
)