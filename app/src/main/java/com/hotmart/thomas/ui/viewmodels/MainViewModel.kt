package com.hotmart.thomas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.models.presentation.ResultState
import com.hotmart.domain.usecases.locations.GetLocationsUseCase
import io.reactivex.rxkotlin.addTo


class MainViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
): BaseViewModel() {

    private val _locationsLiveData = MutableLiveData<ResultState<List<Location>>>()
    val locationsLiveData: LiveData<ResultState<List<Location>>>
        get() = _locationsLiveData

    fun getLocations() {
        _locationsLiveData.postValue(ResultState.Loading())
        getLocationsUseCase.execute()
                .subscribe({
                    _locationsLiveData.postValue(ResultState.Success(it))
                }, {
                    _locationsLiveData.postValue(ResultState.Error(it))
                }).addTo(disposables)
    }


}