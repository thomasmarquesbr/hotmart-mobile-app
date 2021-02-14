package com.hotmart.domain.models.responses

data class LocationsDataResponse(
        val listLocations: List<LocationResponse>
)

data class LocationResponse(
        val id: Int,
        val name: String,
        val review: Double,
        val type: String
)