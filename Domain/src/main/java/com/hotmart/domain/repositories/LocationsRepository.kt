package com.hotmart.domain.repositories

import io.reactivex.Flowable


interface LocationsRepository {

    fun getLocations(): Flowable<Any>

}