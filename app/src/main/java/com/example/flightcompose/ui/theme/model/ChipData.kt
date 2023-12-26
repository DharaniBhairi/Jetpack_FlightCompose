package com.example.flightcompose.ui.theme.model

enum class ChipData(var value: String){
    SingleTrip("single trip"),
    RoundTrip("round trip")
}

fun getAllChips(): List<ChipData>{
    return listOf(ChipData.SingleTrip,ChipData.RoundTrip)
}

fun getData(value: String): ChipData? {
    val map = ChipData.values().associateBy(ChipData::value)
    return map[value]
}