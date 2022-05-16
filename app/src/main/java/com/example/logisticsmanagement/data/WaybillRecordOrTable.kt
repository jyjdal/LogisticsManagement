package com.example.logisticsmanagement.data

import com.fasterxml.jackson.annotation.JsonProperty
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

// 对Waybill进行封装，因为给的数据是如下形式：
// { "waybillRecord": [ waybill, waybill, ... ] }
// <waybillTable> <waybillRecord>...</waybillRecord> <waybillRecord>...</waybillRecord> </waybillTable>
@Root(name = "waybillTable")
data class WaybillRecordOrTable(
    @JsonProperty("waybillRecord")
    @field:ElementList(inline = true)
    var waybillList: MutableList<Waybill> = mutableListOf()
)
