package com.hotmart.domain.models.presentation

import com.hotmart.domain.models.responses.LocationDetailsResponse


class LocationDetails(
    val id: Int,
    val name: String,
    val review: Double,
    val type: String,
    val about: String,
    val phone: String,
    val adress: String,
    val schedule: List<Schedule>
) {

    constructor(locationDetailsResponse: LocationDetailsResponse): this(
        locationDetailsResponse.id,
        locationDetailsResponse.name,
        locationDetailsResponse.review,
        locationDetailsResponse.type,
        locationDetailsResponse.about,
        locationDetailsResponse.phone,
        locationDetailsResponse.adress,
        locationDetailsResponse.schedules.map { scheduleResponse ->
            Schedule(
                ScheduleDay(scheduleResponse.sunday?.open ?: "", scheduleResponse.sunday?.close ?: ""),
                ScheduleDay(scheduleResponse.monday?.open ?: "", scheduleResponse.monday?.close ?: ""),
                ScheduleDay(scheduleResponse.tuesday?.open ?: "", scheduleResponse.tuesday?.close ?: ""),
                ScheduleDay(scheduleResponse.wednesday?.open ?: "", scheduleResponse.wednesday?.close ?: ""),
                ScheduleDay(scheduleResponse.thursday?.open ?: "", scheduleResponse.thursday?.close ?: ""),
                ScheduleDay(scheduleResponse.friday?.open ?: "", scheduleResponse.friday?.close ?: ""),
                ScheduleDay(scheduleResponse.saturday?.open ?: "", scheduleResponse.saturday?.close ?: "")
            )
        }
    )

    var images = listOf(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXefiILSgf-BL6VIzJXZkeNxeWHRk8jQ0UHQ&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxw75y9HXP-Elz9YOQuAs_5gaYHYmZHz2nbQ&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5eYW2piHZILndzOTh52zCE4h3KnRPdb8E7A&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTb9euvN7e2uoMrOapiGZgkwuG1vQO_ORZxDQ&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQV1h62I67PCUmJ4XBPkSku2NtIyLQID8XpiA&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeYEuEbi_mLxVkbkSN9XOuqFlT23KvngDZ9g&usqp=CAU"
    )

    var totalReviews = 146
    var reviews = listOf(
        Review(
            4.9,
            "Thomás Montenegro",
            "Fantástico",
            "Tortas deliciosas. Os waffles também estavam muito bons. Equipe muito atenciosa. :)",
            "Belo Horizonte - MG"
        ),
        Review(
            4.0,
            "Glória Ruiz",
            "Café da manhã delicioso",
            "Nós fomos para o brunch e estava realmente delicioso. Pães, ovos, café, sucos naturais. Não é muito barato mas vale a pena.",
            "São João Del Rey - MG"
        ),
        Review(
            3.8,
            "Shirley Jones",
            "Ótima comida",
            "Comidas frescas e de boa qualidade. Pães e quitandas saindo do forno toda hora. Cafés especiais e ambiente agradável.",
            "Mountain View - CA"
        )
    )

}