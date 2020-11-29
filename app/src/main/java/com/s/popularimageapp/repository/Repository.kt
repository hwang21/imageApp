package com.s.popularimageapp.repository

import com.s.popularimageapp.api.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getData() = apiService.getData("dc6zaTOxFJmzC")
}