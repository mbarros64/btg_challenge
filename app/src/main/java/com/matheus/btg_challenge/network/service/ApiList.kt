package com.matheus.btg_challenge.network.service

import com.matheus.btg_challenge.network.response.ApiListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiList {

    @GET("list")
    suspend fun getApiList(): Response<ApiListResponse>
}