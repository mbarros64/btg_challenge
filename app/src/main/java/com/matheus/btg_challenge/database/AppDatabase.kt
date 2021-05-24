package com.matheus.btg_challenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matheus.btg_challenge.database.dao.CurrencyDao
import com.matheus.btg_challenge.database.entity.Currency

class AppDatabase {
    @Database(entities = [Currency::class], version = 1, exportSchema = false)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun currencyDao() : CurrencyDao
    }
}