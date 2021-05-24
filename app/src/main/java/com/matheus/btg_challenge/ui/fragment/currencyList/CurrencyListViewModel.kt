package com.matheus.btg_challenge.ui.fragment.currencyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheus.btg_challenge.database.dao.CurrencyDao
import com.matheus.btg_challenge.database.entity.Currency

class CurrencyListViewModel (private val listRepository: ListRepository) : ViewModel(){
    val error : MutableLiveData<String> = MutableLiveData()
    val currencyList : MutableLiveData<List<Currency>> = MutableLiveData()

    fun getCurrencyList() : LiveData<List<Currency>> {
        try {
            currencyList.postValue(listRepository.getCurrencyListFromApi().value)
        } catch (e : Exception) {
            error.value = e.message
        }

        return currencyList
    }
}