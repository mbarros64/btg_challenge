package com.matheus.btg_challenge.network

import com.matheus.btg_challenge.network.service.ApiList
import com.matheus.btg_challenge.network.service.ApiLive
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "https://btg-mobile-challenge.herokuapp.com/"

fun provideRetrofit() : Retrofit {
    return Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideCurrencyListService(retrofit: Retrofit) : ApiList = retrofit.create(ApiList::class.java)
fun provideCurrencyLiveService(retrofit: Retrofit) : ApiLive = retrofit.create(ApiLive::class.java)