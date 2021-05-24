package com.matheus.btg_challenge.repository

import com.matheus.btg_challenge.network.service.ApiLive
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.matheus.btg_challenge.database.dao.CurrencyDao
import com.matheus.btg_challenge.database.entity.Currency

class HomeRepository(
    private val localData: CurrencyDao,
    private val remoteCurrencyRate: ApiLive,
    private val remoteCurrencyList: ApiList
) {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private val util = com.example.currencyapp.utils.util()

    fun getExchangeRateValues() : LiveData<List<Currency>> {
        try {
            val list: MutableLiveData<List<Currency>> = MutableLiveData()
            scope.launch {

                getCurrencyRemoteResources()


                list.value = getLocalCurrencies()
            }

            println("List $list")
            return list
        } catch (e: Throwable) {
            throw Exception(e)
        }
    }

    private suspend fun getLocalCurrencies(): List<Currency> {
        try {
            return localData.getAllCurrencies()
        } catch (e: Throwable) {
            throw Exception(e)
        }
    }


    private suspend fun getCurrencyRemoteResources() {

        try {
            val quotesLiveResponse = remoteCurrencyRate.getCurrencyLive()
            val currencyNameResponse = remoteCurrencyList.getCurrencyList()

            if (quotesLiveResponse.isSuccessful) {
                println("First if ok")
                if (quotesLiveResponse.body()?.success == true && currencyNameResponse.body()?.success == true) {
                    println("Second if ok")
                    val currencyList : MutableList<Currency> = mutableListOf()

                    val quotes = quotesLiveResponse.body()?.quotes
                    val currenciesNameList = currencyNameResponse.body()?.currencies

                    println("quotes $quotes / namelist $currenciesNameList")

                    val quotesKeys: MutableList<String> = mutableListOf()


                    quotes?.let {
                        quotesKeys.addAll(it.keys.toList())

                        println("QUOTES KEYS $quotesKeys")

                        for (key in quotesKeys) {
                            val currencyInitials = key.substring(key.length / 2)

                            println("INITIALS $currencyInitials")
                            val currency =
                                Currency(
                                    currency = currencyInitials,
                                    currencyName = currenciesNameList!![currencyInitials]!!,
                                    rate = it[key]!!)

                            println("Currency $currency")

                            currencyList.add(currency)
                        }

                        updateLocalDatabase(currencies = currencyList)
                    } ?: throw Exception("Empty List")
                }
            }
        } catch (e: Throwable) {
            println("THROW REMOTE RESOURCES : $e")
        }

    }

    private suspend fun updateLocalDatabase(currencies: List<Currency>) {
        try {
            localData.updateAllRate(currencies)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}