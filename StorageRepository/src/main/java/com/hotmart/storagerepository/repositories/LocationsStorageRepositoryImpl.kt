package com.hotmart.storagerepository.repositories

import com.hotmart.datarepository.storageRepository.LocationsStorageRepository
import com.hotmart.domain.models.entities.LocationEntity
import com.vicpin.krealmextensions.deleteAll
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.saveAll


class LocationsStorageRepositoryImpl: LocationsStorageRepository {

    override fun getLocations(): List<LocationEntity> =
        LocationEntity().queryAll()

    override fun saveLocations(locations: List<LocationEntity>) {
        locations.saveAll()
    }

    override fun deleteLocations() {
        LocationEntity().deleteAll()
    }

}