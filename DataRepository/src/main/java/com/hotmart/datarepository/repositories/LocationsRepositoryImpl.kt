package com.hotmart.datarepository.repositories

import com.hotmart.datarepository.remoteRepository.LocationsRemoteRepository
import com.hotmart.datarepository.storageRepository.LocationsStorageRepository
import com.hotmart.domain.repositories.LocationsRepository
import io.reactivex.Flowable


class LocationsRepositoryImpl(
    private val storage: LocationsStorageRepository,
    private val remote: LocationsRemoteRepository
): LocationsRepository {

    override fun getLocations(): Flowable<Any> {
        return Flowable.empty()
    }

}