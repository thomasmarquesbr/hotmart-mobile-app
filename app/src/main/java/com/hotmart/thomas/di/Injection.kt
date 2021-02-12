package com.hotmart.thomas.di

import com.hotmart.datarepository.remoteRepository.LocationsRemoteRepository
import com.hotmart.datarepository.repositories.LocationsRepositoryImpl
import com.hotmart.datarepository.storageRepository.LocationsStorageRepository
import com.hotmart.domain.repositories.LocationsRepository
import com.hotmart.domain.usecases.base.JobExecutor
import com.hotmart.domain.usecases.base.PostExecutionThread
import com.hotmart.domain.usecases.base.ThreadExecutor
import com.hotmart.domain.usecases.base.UiThread
import com.hotmart.domain.usecases.locations.GetLocationsUseCase
import com.hotmart.remoterepository.api.ApiServiceFactory
import com.hotmart.remoterepository.repositories.LocationsRemoteRepositoryImpl
import com.hotmart.storagerepository.repositories.LocationsStorageRepositoryImpl
import com.hotmart.thomas.BuildConfig
import com.hotmart.thomas.ui.viewmodels.MainViewModel
import io.realm.Realm
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val HOTMART_DB_NAME = "hotmart_realm"
const val HOTMART_DB_VERSION = 1L

val storageModule = module(override = true) {
    single { Realm.getDefaultInstance() }

    factory<LocationsStorageRepository> { LocationsStorageRepositoryImpl() }
}

val remoteModule = module(override = true) {
    factory { ApiServiceFactory.makeService(BuildConfig.DEBUG, BuildConfig.API_URL) }

    factory<LocationsRemoteRepository> { LocationsRemoteRepositoryImpl(get()) }
}

val dataModule = module(override = true) {
    factory<LocationsRepository> { LocationsRepositoryImpl(get(), get()) }
}

val domainModule = module(override = true) {
    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }

    factory { GetLocationsUseCase(get(), get(), get()) }
}

val presentationModule = module(override = true) {
    viewModel { MainViewModel(get()) }
}