package com.matheus.btg_challenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matheus.btg_challenge.network.service.ApiList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.matheus.btg_challenge.database.dao.CurrencyDao
import com.matheus.btg_challenge.database.entity.Currency

class ListRepository(

    private val localData: CurrencyDao,

    ) {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)


    fun getCurrencyListFromApi(): LiveData<List<Currency>> {

        val list: MutableLiveData<List<Currency>> = MutableLiveData()

        scope.launch {

            try {

                list.value = getLocalCurrencies()


            } catch (e: Throwable) {

                println("THROW $e")

            }

        }


        return list

    }


    private suspend fun getLocalCurrencies(): List<Currency> {

        try {

            return localData.getAllCurrencies()

        } catch (e: Throwable) {

            throw Exception(e)

        }

    }

}