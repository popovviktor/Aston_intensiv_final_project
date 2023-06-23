package com.myapplication.finalproject.featureLocation.domain.usecase

import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import javax.inject.Inject

class GetLocationsNewPageUseCase @Inject constructor(private val repositoryLocations: RepositoryLocations) {
    suspend fun execute(urlNewPage:String):LocationsDomain?{
        return repositoryLocations.getNewPageLocationFromWeb(urlNewPage)
    }
}