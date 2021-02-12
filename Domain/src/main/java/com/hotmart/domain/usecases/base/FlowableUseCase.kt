package com.hotmart.domain.usecases.base

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers


abstract class FlowableUseCase<T> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    protected fun subscribeAndObserve(flowable: Flowable<T>): Flowable<T> = flowable
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.scheduler)

}