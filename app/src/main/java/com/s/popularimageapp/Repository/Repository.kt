package com.s.popularimageapp.Repository

import com.s.popularimageapp.api.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getData() = apiService.getData("dc6zaTOxFJmzC")
}