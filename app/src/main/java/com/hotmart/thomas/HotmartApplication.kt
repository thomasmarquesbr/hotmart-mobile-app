package com.hotmart.thomas

import android.app.Application
import com.hotmart.storagerepository.configureStorage
import com.hotmart.thomas.di.*
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class HotmartApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        configureStorage(HOTMART_DB_NAME, HOTMART_DB_VERSION)
        startKoin {
            androidLogger()
            androidContext(this@HotmartApplication)
            modules(listOf(storageModule, remoteModule, dataModule, domainModule, presentationModule))
        }
    }

}