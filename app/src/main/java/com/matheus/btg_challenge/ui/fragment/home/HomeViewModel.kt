package com.matheus.btg_challenge.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheus.btg_challenge.database.dao.CurrencyDao
import com.matheus.btg_challenge.database.entity.Currency
import com.matheus.btg_challenge.repository.HomeRepository


class HomeViewModel (private val homeRepository: HomeRepository) : ViewModel() {
    val currencies: MutableLiveData<List<Currency>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun getCurrencies() {
        try {
            currencies.value = homeRepository.getExchangeRateValues().value
        } catch (e: Exception) {
            error.value = e.message
        }
    }

    fun convertCurrencyAtoCurrencyB(
        input: Number,
        currencyAToUSDTaxes: Number,
        currencyUSDToBTaxes: Number
    ): Double {
        var inputInUSD: Double = 0.0
        var result: Double = 0.0

        inputInUSD = ((input.toDouble()) / currencyAToUSDTaxes.toDouble())
        result = (inputInUSD * currencyUSDToBTaxes.toDouble())

        return result
    }
}