package com.myapplication.finalproject.featureLocation.domain.usecase

import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import javax.inject.Inject

class GetLocationWebUseCase @Inject constructor(private val repositoryLocations: RepositoryLocations) {
    suspend fun execute(url:String): LocationDomain?{
        return repositoryLocations.getLocationWeb(url)
    }
}