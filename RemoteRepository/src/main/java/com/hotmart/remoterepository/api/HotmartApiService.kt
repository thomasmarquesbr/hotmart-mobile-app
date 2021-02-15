package com.hotmart.remoterepository.api

import com.hotmart.domain.models.responses.LocationDetailsResponse
import com.hotmart.domain.models.responses.LocationsDataResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path


interface HotmartApiService {

    @GET("/locations")
    fun getLocations(): Flowable<LocationsDataResponse>

    @GET("/locations/{id}")
    fun getLocationDetails(
        @Path("id") id: Int
    ): Flowable<LocationDetailsResponse>

}