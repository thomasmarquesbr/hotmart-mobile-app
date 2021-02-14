package com.hotmart.domain.repositories

import com.hotmart.domain.models.presentation.Location
import io.reactivex.Flowable


interface LocationsRepository {

    fun getLocations(): Flowable<List<Location>>

}