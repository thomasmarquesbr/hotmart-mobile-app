package com.hotmart.domain.models.responses

data class LocationDetailsResponse(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val adress: String,
    val schedule: List<ScheduleResponse>
)

data class ScheduleResponse(
    val sunday: ScheduleDayResponse,
    val monday: ScheduleDayResponse,
    val tuesday: ScheduleDayResponse,
    val wednesday: ScheduleDayResponse,
    val thursday: ScheduleDayResponse,
    val friday: ScheduleDayResponse,
    val saturday: ScheduleDayResponse,
)

data class ScheduleDayResponse(
    val open: String,
    val close: String
)