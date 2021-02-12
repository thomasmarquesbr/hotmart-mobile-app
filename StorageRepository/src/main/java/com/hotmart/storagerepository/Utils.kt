package com.hotmart.storagerepository

import io.realm.Realm
import io.realm.RealmConfiguration


fun configureStorage(dbName: String, dbVersion: Long) {
    val configuration = RealmConfiguration.Builder()
        .name(dbName)
        .schemaVersion(dbVersion)
        .deleteRealmIfMigrationNeeded()
        .build()
    Realm.setDefaultConfiguration(configuration)
}