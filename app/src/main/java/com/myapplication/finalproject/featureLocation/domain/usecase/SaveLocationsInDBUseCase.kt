package com.myapplication.finalproject.featureLocation.domain.usecase

import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import javax.inject.Inject

class SaveLocationsInDBUseCase @Inject constructor(private val repositoryLocations: RepositoryLocations) {
    suspend fun execute(locationsDomain: LocationsDomain){
        repositoryLocations.saveDataLocationsInDB(locationsDomain)
    }
}