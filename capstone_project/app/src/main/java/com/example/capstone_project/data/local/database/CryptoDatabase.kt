package com.example.capstoneproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.capstoneproject.data.local.DAO.AskDao
import com.example.capstoneproject.data.local.DAO.BidDao
import com.example.capstoneproject.data.local.DAO.BookDao
import com.example.capstoneproject.data.local.DAO.TickerDao
import com.example.capstoneproject.data.local.entities.AskEntity
import com.example.capstoneproject.data.local.entities.BidsEntity
import com.example.capstoneproject.data.local.entities.BookEntity
import com.example.capstoneproject.data.local.entities.TickerEntity

@Database(entities = [TickerEntity::class, BidsEntity::class, AskEntity::class, BookEntity::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun getTickerDao(): TickerDao
    abstract fun getBookDao(): BookDao
    abstract fun getAskDao(): AskDao
    abstract fun getBidDao(): BidDao

    companion object {
        const val DATABASE_NAME = "cryptodatabase.db"
    }
}
