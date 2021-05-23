package com.matheus.btg_challenge.ui.fragment.home

class HomeViewModel {

    fun convertCurrencyAtoCurrencyB(input : Number, currencyAToUSDTaxes : Number, currencyUSDToBTaxes : Number) : Double{
        var inputInUSD : Double = 0.0
        var result : Double = 0.0

        inputInUSD = ((input.toDouble()) / currencyAToUSDTaxes.toDouble())
        result = (inputInUSD * currencyUSDToBTaxes.toDouble())

        return result
    }
}