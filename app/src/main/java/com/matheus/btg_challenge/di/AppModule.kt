package com.matheus.btg_challenge.di

import com.matheus.btg_challenge.network.provideCurrencyListService
import com.matheus.btg_challenge.network.provideCurrencyLiveService
import com.matheus.btg_challenge.network.provideRetrofit
import com.matheus.btg_challenge.repository.ListRepository
import org.koin.dsl.module


val networkModule = module {
    factory { provideCurrencyLiveService(get()) }
    factory { provideCurrencyListService(get()) }
    single { provideRetrofit() }
}

val currencyListModule = module {
    single { ListRepository(get()) }
}