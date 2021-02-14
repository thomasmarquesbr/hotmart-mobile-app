package com.hotmart.remoterepository.repositories

import com.hotmart.datarepository.remoteRepository.LocationsRemoteRepository
import com.hotmart.domain.models.responses.LocationResponse
import com.hotmart.remoterepository.api.HotmartApiService
import io.reactivex.Flowable


class LocationsRemoteRepositoryImpl(
    private val apiService: HotmartApiService
): LocationsRemoteRepository {

    override fun getLocations(): Flowable<List<LocationResponse>> = apiService
            .getLocations()
            .map { it.listLocations }

}