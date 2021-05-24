package com.matheus.btg_challenge.database.dao
import androidx.room.*
import com.matheus.btg_challenge.database.entity.Currency

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllRate(currencies: List<Currency>)

    @Query("SELECT * FROM currency_table")
    suspend fun getAllCurrencies() : List<Currency>

}