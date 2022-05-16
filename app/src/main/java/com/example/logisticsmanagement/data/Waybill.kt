package com.example.logisticsmanagement.data

import androidx.room.Entity
import com.fasterxml.jackson.annotation.JsonProperty
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Entity
@Root(name = "waybillRecord")
data class Waybill(
    // 必选项
    @JsonProperty("goodsName") @field:Element(name = "goodsName")
    var name: String = "",  // 货物名称
    @JsonProperty("numberOfPackages") @field:Element(name = "numberOfPackages")
    var count: Int = 0,  // 数量
    @JsonProperty("transportationDepartureStation") @field:Element(name = "transportationDepartureStation")
    var src: String = "",  // 起点
    @JsonProperty("transportationArrivalStation") @field:Element(name = "transportationArrivalStation")
    var dest: String = "",  // 终点

    // 本地录入运单的可选项
    @JsonProperty("consignor") @field:Element(name = "consignor")
    var consignor: String? = "",
    @JsonProperty("consignorPhoneNumber") @field:Element(name = "consignorPhoneNumber")
    var consignorPhoneNumber: String? = "",
    @JsonProperty("consignee") @field:Element(name = "consignee")
    var consignee: String? = "",
    @JsonProperty("consigneePhoneNumber") @field:Element(name = "consigneePhoneNumber")
    var consigneePhoneNumber: String? = "",
    @JsonProperty("freightPaidByTheReceivingParty") @field:Element(name = "freightPaidByTheReceivingParty")
    var freightPaidByTheReceivingParty: Int? = 0,
    @JsonProperty("freightPaidByConsignor") @field:Element(name = "freightPaidByConsignor")
    var freightPaidByConsignor: Int? = 0,

    // 本地运单中没有，只在在线运单中存在的属性
    @JsonProperty("waybillNo") @field:Element(name = "waybillNo")
    var waybillNo: String? = "",
    @JsonProperty("goodsDistributionAddress") @field:Element(name = "goodsDistributionAddress")
    var goodsDistributionAddress: String? = ""
)