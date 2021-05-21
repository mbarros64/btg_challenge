package com.matheus.btg_challenge.network.service

import com.matheus.btg_challenge.network.response.ApiLiveResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiLive {

    @GET("live")
    suspend fun getApiLive(): Response<ApiLiveResponse>
}