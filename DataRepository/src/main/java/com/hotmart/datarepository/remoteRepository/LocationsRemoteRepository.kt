package com.hotmart.datarepository.remoteRepository

import com.hotmart.domain.models.responses.LocationDetailsResponse
import com.hotmart.domain.models.responses.LocationResponse
import io.reactivex.Flowable


interface LocationsRemoteRepository {

    fun getLocations(): Flowable<List<LocationResponse>>
    fun getLocationDetails(id: Int): Flowable<LocationDetailsResponse>

}