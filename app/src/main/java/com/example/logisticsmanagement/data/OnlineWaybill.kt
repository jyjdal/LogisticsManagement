package com.example.logisticsmanagement.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * 运单实体类，分为三部分：
 *   第一部分是必选项
 *   第二部分是可选项
 *   第三部分在本地运单中没有属性，只在在线运单中可见
 */
@Entity
@Root(name = "waybillRecord")
data class OnlineWaybill(
    // 必选项
    @JsonProperty("goodsName") @field:Element(name = "goodsName")
    var name: String = "",  // 货物名称
    @JsonProperty("numberOfPackages") @field:Element(name = "numberOfPackages")
    var count: Int = 0,  // 数量
    @JsonProperty("transportationDepartureStation") @field:Element(name = "transportationDepartureStation")
    var src: String = "",  // 起点
    @JsonProperty("transportationArrivalStation") @field:Element(name = "transportationArrivalStation")
    var dest: String = "",  // 终点
    @JsonProperty("consignor") @field:Element(name = "consignor")
    var consignor: String = "",
    @JsonProperty("consignorPhoneNumber") @field:Element(name = "consignorPhoneNumber")
    var consignorPhoneNumber: String = "",
    @JsonProperty("consignee") @field:Element(name = "consignee")
    var consignee: String = "",
    @JsonProperty("consigneePhoneNumber") @field:Element(name = "consigneePhoneNumber")
    var consigneePhoneNumber: String = "",
    @JsonProperty("freightPaidByTheReceivingParty") @field:Element(name = "freightPaidByTheReceivingParty")
    var freightPaidByTheReceivingParty: Int = 0,
    @JsonProperty("freightPaidByConsignor") @field:Element(name = "freightPaidByConsignor")
    var freightPaidByConsignor: Int = 0,
    @JsonProperty("waybillNo") @field:Element(name = "waybillNo")
    var waybillNo: String = "",
    @JsonProperty("goodsDistributionAddress") @field:Element(name = "goodsDistributionAddress")
    var goodsDistributionAddress: String = ""
)