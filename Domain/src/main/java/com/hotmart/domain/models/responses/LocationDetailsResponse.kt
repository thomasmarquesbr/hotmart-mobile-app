package com.hotmart.domain.models.responses

import com.google.gson.Gson
import org.json.JSONArray


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

    /** Em alguns objetos retornados pela API REST o objeto "schedule" retorna tanto um Array quanto
     * um Objeto json para alguns "ids", portanto foi necessário a adaptação abaixo para mapear cada
     * um desses tipos de estruturas de dados
     */
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