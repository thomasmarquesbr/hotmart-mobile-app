package com.hotmart.datarepository.repositories

import com.hotmart.datarepository.remoteRepository.LocationsRemoteRepository
import com.hotmart.datarepository.storageRepository.LocationsStorageRepository
import com.hotmart.domain.models.entities.LocationEntity
import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.repositories.LocationsRepository
import io.reactivex.Flowable


class LocationsRepositoryImpl(
    private val storage: LocationsStorageRepository,
    private val remote: LocationsRemoteRepository
): LocationsRepository {

    /**
     * O ideal seria utilizar um recurso de versionamento dos dados que seja suportado
     * pela API REST para carregar apenas a lista local quando possível.
     * Um exemplo desse versionamento seria o envio do status 304 do
     * http/https, onde os dados seriam enviados de acordo com o timestamp da última
     * requisição de dados realizada pelo cliente. Com isso, nesse ponto, seria verificado
     * o status 304 caso a versão dos dados na api seja a mesma persistida no App ou
     * capturaria 200 com a lista em si caso lista seja uma nova versão de locations
     */
    override fun getLocations(): Flowable<List<Location>> = remote
            .getLocations()
            .map { res ->
                val listLocationEntity = res.map { LocationEntity(it) }
                storage.deleteLocations()
                storage.saveLocations(listLocationEntity)
                listLocationEntity
            }.map { list ->
                list.map { Location(it) }
            }

}