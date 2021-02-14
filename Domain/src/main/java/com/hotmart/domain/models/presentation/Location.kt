package com.hotmart.domain.models.presentation

import com.hotmart.domain.models.entities.LocationEntity


class Location(
        val id: Int,
        val name: String,
        val review: Double,
        val type: String
) {

    constructor(locationResponse: LocationEntity): this(
            locationResponse.id ?: -1,
            locationResponse.name ?: "",
            locationResponse.review ?: 0.0,
            locationResponse.type ?: ""
    )

}