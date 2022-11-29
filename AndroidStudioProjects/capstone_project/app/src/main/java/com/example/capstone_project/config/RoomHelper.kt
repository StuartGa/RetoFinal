package com.example.capstone_project.config

import androidx.room.Room
import com.example.capstone_project.data.local.database.CryptoDatabase
import com.example.capstone_project.presentation.Util

object RoomHelper {
    private val CRYPTO_DATABASE_NAME = "cryptodatabase.db"
    var databaseInstance: CryptoDatabase? = null

    fun provideDatabase(): CryptoDatabase {
        synchronized(CryptoDatabase::class.java) {
            databaseInstance = Room.databaseBuilder(Util.instance, CryptoDatabase::class.java, CRYPTO_DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        return databaseInstance!!
    }
}
