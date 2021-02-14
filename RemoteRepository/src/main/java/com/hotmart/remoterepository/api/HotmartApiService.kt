package com.hotmart.remoterepository.api

import com.hotmart.domain.models.responses.LocationsDataResponse
import io.reactivex.Flowable
import retrofit2.http.GET


interface HotmartApiService {

    @GET("/locations")
    fun getLocations(): Flowable<LocationsDataResponse>

}