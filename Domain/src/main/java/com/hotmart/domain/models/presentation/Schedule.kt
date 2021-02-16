package com.hotmart.domain.models.presentation

class Schedule(
    val sunday: ScheduleDay,
    val monday: ScheduleDay,
    val tuesday: ScheduleDay,
    val wednesday: ScheduleDay,
    val thursday: ScheduleDay,
    val friday: ScheduleDay,
    val saturday: ScheduleDay,
) {

    fun convertToMap(map: MutableMap<String, String>, daysArray: Array<String>): MutableMap<String, String> {
        val key0 = "${this.sunday.open}-${this.sunday.close}"
        val key1 = "${this.monday.open}-${this.monday.close}"
        val key2 = "${this.tuesday.open}-${this.tuesday.close}"
        val key3 = "${this.wednesday.open}-${this.wednesday.close}"
        val key4 = "${this.thursday.open}-${this.thursday.close}"
        val key5 = "${this.friday.open}-${this.friday.close}"
        val key6 = "${this.saturday.open}-${this.saturday.close}"
        if (!map.containsKey(key0))
            map[key0] = ""
        if (!map.containsKey(key1))
            map[key1] = ""
        if (!map.containsKey(key2))
            map[key2] = ""
        if (!map.containsKey(key3))
            map[key3] = ""
        if (!map.containsKey(key4))
            map[key4] = ""
        if (!map.containsKey(key5))
            map[key5] = ""
        if (!map.containsKey(key6))
            map[key6] = ""
        map[key0] += "${daysArray[0]}, "
        map[key1] += "${daysArray[1]}, "
        map[key2] += "${daysArray[2]}, "
        map[key3] += "${daysArray[3]}, "
        map[key4] += "${daysArray[4]}, "
        map[key5] += "${daysArray[5]}, "
        map[key6] += "${daysArray[6]}, "
        return map
    }
    
}