package com.matheus.btg_challenge.repository

import com.matheus.btg_challenge.network.service.ApiLive
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeRepository(private val remoteCurrencyLive: ApiLive) {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)


    fun getCurrencyLiveFromApi() {
        scope.launch {
            try {
                val currencyLive = remoteCurrencyLive.getApiLive()

                if (currencyLive.isSuccessful) {
                    val currencies = currencyLive.body()?.quotes
                    val currencyKeys: MutableList<String> = mutableListOf()
                    val currencyLiveList: MutableList<Map<String, Double?>> = mutableListOf()

                    currencies?.let {
                        currencyKeys.addAll(it.keys.toList())

                        for (key in currencyKeys)
                            currencyLiveList.add(mapOf(key.substring(key.length / 2) to it[key]))
                    }

                    println("list $currencyLiveList")
                }
            } catch (e: Throwable) {
                println("THROW $e")
            }
        }
    }
}