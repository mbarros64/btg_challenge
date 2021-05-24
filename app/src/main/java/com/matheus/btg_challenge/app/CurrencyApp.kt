package com.matheus.btg_challenge.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.matheus.btg_challenge.database.dao.CurrencyDao
import com.matheus.btg_challenge.database.entity.Currency
import com.matheus.btg_challenge.di.*

class CurrencyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CurrencyApp)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    resourceModule,
                    viewModelModule

                )
            )
        }
    }
}