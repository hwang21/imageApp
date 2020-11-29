package com.s.popularimageapp.api

import com.s.popularimageapp.model.GiphyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending")
    suspend fun getData(
        @Query("api_key") apiKey: String): GiphyResponse

}