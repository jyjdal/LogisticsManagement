package com.example.logisticsmanagement.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * 员工实体类，包含姓名、工号、密码等信息
 * 其中[jobNumber]表示工号，相当于登录的账号
 */
@Entity(tableName = "employee", indices = [Index(value = ["jobNumber"], unique = true)])
data class Employee(
    @PrimaryKey val id: Int,

    val name: String,

    // 用于登录的账号，要求是唯一字段
    val jobNumber: String,

    val password: String,

    val department: String?,

    val telephone: String?
)