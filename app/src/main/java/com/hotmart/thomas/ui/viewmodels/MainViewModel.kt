package com.hotmart.thomas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.models.presentation.LocationDetails
import com.hotmart.domain.models.presentation.ResultState
import com.hotmart.domain.usecases.locations.GetLocationDetailsUseCase
import com.hotmart.domain.usecases.locations.GetLocationsUseCase
import io.reactivex.rxkotlin.addTo


class MainViewModel(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase
): BaseViewModel() {

    private val _locationsLiveData = MutableLiveData<ResultState<List<Location>>>()
    private val _locationDetailsLiveData = MutableLiveData<ResultState<LocationDetails>>()
    val locationsLiveData: LiveData<ResultState<List<Location>>> get() = _locationsLiveData
    val locationDetailsLiveData: LiveData<ResultState<LocationDetails>> get() = _locationDetailsLiveData

    fun getLocations() {
        _locationsLiveData.postValue(ResultState.Loading())
        getLocationsUseCase.execute()
                .subscribe({
                    _locationsLiveData.postValue(ResultState.Success(it))
                }, {
                    _locationsLiveData.postValue(ResultState.Error(it))
                }).addTo(disposables)
    }

    fun getDetails(location: Location) {
        _locationDetailsLiveData.postValue(ResultState.Loading())
        getLocationDetailsUseCase.execute(location)
            .subscribe({
                _locationDetailsLiveData.postValue(ResultState.Success(it))
            }, {
                _locationDetailsLiveData.postValue(ResultState.Error(it))
            }).addTo(disposables)
    }


}