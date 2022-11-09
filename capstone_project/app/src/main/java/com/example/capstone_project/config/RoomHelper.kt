package com.example.capstone_project.config

import android.content.Context
import androidx.room.Room
import com.example.capstone_project.data.local.database.CryptoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/*
@Module
@InstallIn(SingletonComponent::class)
object RoomHelper {
    val CRYPTO_DATABASE_NAME = "cryptodatabase.db"
    var databaseInstance: CryptoDatabase? = null

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CryptoDatabase =
        Room.databaseBuilder(
            context,
            CryptoDatabase::class.java,
            CRYPTO_DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
}*/

