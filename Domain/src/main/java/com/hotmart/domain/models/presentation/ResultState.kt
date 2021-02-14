package com.hotmart.domain.models.presentation


sealed class ResultState<T1>(
        val resourceState: ResourceState,
        val data: T1? = null,
        val errorMessage: String? = null
) {

    data class Success<T1>(private val dataResult: T1):
            ResultState<T1>(ResourceState.SUCCESS, dataResult)

    data class Error<T1>(private val throwable: Throwable): ResultState<T1>(
            ResourceState.ERROR, errorMessage = throwable.localizedMessage)

    data class Loading<T1>(private val resState: ResourceState? = null):
            ResultState<T1>(ResourceState.LOADING)

}