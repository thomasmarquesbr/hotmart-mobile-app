package com.hotmart.domain.usecases.locations

import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.models.presentation.LocationDetails
import com.hotmart.domain.repositories.LocationsRepository
import com.hotmart.domain.usecases.base.FlowableUseCase
import com.hotmart.domain.usecases.base.PostExecutionThread
import com.hotmart.domain.usecases.base.ThreadExecutor
import io.reactivex.Flowable


class GetLocationDetailsUseCase(
    private val locationsRepository: LocationsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): FlowableUseCase<LocationDetails>(threadExecutor, postExecutionThread) {

    fun execute(location: Location): Flowable<LocationDetails> = subscribeAndObserve(
        locationsRepository.getLocationDetails(location.id)
    )

}