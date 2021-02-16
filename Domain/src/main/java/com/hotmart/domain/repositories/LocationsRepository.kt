package com.hotmart.domain.repositories

import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.models.presentation.LocationDetails
import io.reactivex.Flowable


interface LocationsRepository {

    fun getLocations(): Flowable<List<Location>>
    fun getLocationDetails(id: Int): Flowable<LocationDetails>

}