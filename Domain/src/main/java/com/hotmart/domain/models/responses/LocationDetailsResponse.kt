package com.hotmart.domain.models.responses

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type


data class LocationDetailsResponse(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val adress: String,
    val schedule: Any
) {

    val schedules: List<ScheduleResponse>
        get() {
            return if (schedule is List<*>) {
                val jsonArray = JSONArray()
                for (i in 0 until schedule.size) jsonArray.put(schedule[i])
                val list = mutableListOf<ScheduleResponse>()
                for (i in 0 until jsonArray.length()) {
                    val jsonString = jsonArray.get(i).toString()
                    list.add(Gson().fromJson(jsonString, ScheduleResponse::class.java))
                }
                list
            } else {
                val scheduleMap = schedule as Map<String, Map<String, String>>
                val mapper = Gson().toJsonTree(scheduleMap)
                val scheduleResponse = Gson().fromJson(mapper, ScheduleResponse::class.java)
                listOf(scheduleResponse)
            }
        }

}

data class ScheduleResponse(
    val sunday: ScheduleDayResponse?,
    val monday: ScheduleDayResponse?,
    val tuesday: ScheduleDayResponse?,
    val wednesday: ScheduleDayResponse?,
    val thursday: ScheduleDayResponse?,
    val friday: ScheduleDayResponse?,
    val saturday: ScheduleDayResponse?,
)

data class ScheduleDayResponse(
    val open: String,
    val close: String
)