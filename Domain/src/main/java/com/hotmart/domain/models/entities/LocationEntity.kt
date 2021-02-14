package com.hotmart.domain.models.entities

import com.hotmart.domain.models.responses.LocationResponse
import io.realm.RealmObject

open class LocationEntity(
        var id: Int? = null,
        var name: String? = null,
        var review: Double? = null,
        var type: String? = null
): RealmObject() {

        constructor(locationResponse: LocationResponse): this(
                locationResponse.id,
                locationResponse.name,
                locationResponse.review,
                locationResponse.type
        )

}