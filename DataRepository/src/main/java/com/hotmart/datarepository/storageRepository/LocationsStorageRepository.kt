package com.hotmart.datarepository.storageRepository

import com.hotmart.domain.models.entities.LocationEntity
import io.reactivex.Flowable


interface LocationsStorageRepository {

    fun getLocations(): List<LocationEntity>

    fun saveLocations(locations: List<LocationEntity>)

    fun deleteLocations()

}