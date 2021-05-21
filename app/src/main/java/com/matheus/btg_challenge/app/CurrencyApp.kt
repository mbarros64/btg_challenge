package com.matheus.btg_challenge.app

import android.app.Application
import com.matheus.btg_challenge.di.currencyListModule
import com.matheus.btg_challenge.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CurrencyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CurrencyApp)
            modules(
                listOf(
                    networkModule,
                    currencyListModule
                )
            )
        }
    }
}