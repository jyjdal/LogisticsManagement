package com.example.logisticsmanagement.data

import androidx.room.Entity
import com.fasterxml.jackson.annotation.JsonProperty
import javax.xml.bind.annotation.XmlElement

@Entity
data class Waybill(
    // 必选项
    @JsonProperty("goodsName") @XmlElement(name = "goodsName")
    val name: String,  // 货物名称
    @JsonProperty("numberOfPackages") @XmlElement(name = "numberOfPackages")
    val count: Int,  // 数量
    @JsonProperty("transportationDepartureStation") @XmlElement(name = "transportationDepartureStation")
    val src: String,  // 起点
    @JsonProperty("transportationArrivalStation") @XmlElement(name = "transportationArrivalStation")
    val dest: String,  // 终点

    // 本地录入运单的可选项
    @JsonProperty("consignor") @XmlElement(name = "consignor")
    val consignor: String?,
    @JsonProperty("consignorPhoneNumber") @XmlElement(name = "consignorPhoneNumber")
    val consignorPhoneNumber: String?,
    @JsonProperty("consignee") @XmlElement(name = "consignee")
    val consignee: String?,
    @JsonProperty("consigneePhoneNumber") @XmlElement(name = "consigneePhoneNumber")
    val consigneePhoneNumber: String?,
    @JsonProperty("freightPaidByTheReceivingParty") @XmlElement(name = "freightPaidByTheReceivingParty")
    val freightPaidByTheReceivingParty: Int?,
    @JsonProperty("freightPaidByConsignor") @XmlElement(name = "freightPaidByConsignor")
    val freightPaidByConsignor: Int?,

    // 本地运单中没有，只在在线运单中存在的属性
    @JsonProperty("waybillNo") @XmlElement(name = "waybillNo")
    val waybillNo: String?,
    @JsonProperty("goodsDistributionAddress") @XmlElement(name = "goodsDistributionAddress")
    val goodsDistributionAddress: String?
)