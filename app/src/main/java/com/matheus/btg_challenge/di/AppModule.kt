package com.matheus.btg_challenge.di

import androidx.room.Room
import com.matheus.btg_challenge.database.AppDatabase
import com.matheus.btg_challenge.network.provideCurrencyListService
import com.matheus.btg_challenge.network.provideCurrencyLiveService
import com.matheus.btg_challenge.network.provideRetrofit
import com.matheus.btg_challenge.repository.HomeRepository
import com.matheus.btg_challenge.repository.ListRepository
import com.matheus.btg_challenge.ui.fragment.currencyList.CurrencyListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    factory { provideCurrencyLiveService(get()) }
    factory { provideCurrencyListService(get()) }
    single { provideRetrofit() }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            //BuildConfig.DATABASE_NAME
            "currency-database"
        ).build()
    }
}

val resourceModule = module {
    single { get<AppDatabase>().currencyDao() }

    single { ListRepository(get()) }

    single { HomeRepository(get(), get(), get()) }
}

val viewModelModule = module {
    viewModel { CurrencyListViewModel(get()) }
}
