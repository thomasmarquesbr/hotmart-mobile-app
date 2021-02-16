package com.hotmart.domain.usecases.locations

import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.repositories.LocationsRepository
import com.hotmart.domain.usecases.base.FlowableUseCase
import com.hotmart.domain.usecases.base.PostExecutionThread
import com.hotmart.domain.usecases.base.ThreadExecutor
import io.reactivex.Flowable


class GetLocationsUseCase(
    private val locationsRepository: LocationsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): FlowableUseCase<List<Location>>(threadExecutor, postExecutionThread) {

    fun execute(): Flowable<List<Location>> = subscribeAndObserve(
        locationsRepository.getLocations()
    )

}