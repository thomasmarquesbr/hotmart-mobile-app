package com.hotmart.remoterepository.repositories

import com.hotmart.datarepository.remoteRepository.LocationsRemoteRepository
import com.hotmart.domain.models.responses.LocationDetailsResponse
import com.hotmart.domain.models.responses.LocationResponse
import com.hotmart.remoterepository.api.HotmartApiService
import io.reactivex.Flowable


class LocationsRemoteRepositoryImpl(
    private val apiService: HotmartApiService
): LocationsRemoteRepository {

    override fun getLocations(): Flowable<List<LocationResponse>> = apiService
            .getLocations()
            .map { it.listLocations }

    override fun getLocationDetails(id: Int): Flowable<LocationDetailsResponse> = apiService
        .getLocationDetails(id)

}