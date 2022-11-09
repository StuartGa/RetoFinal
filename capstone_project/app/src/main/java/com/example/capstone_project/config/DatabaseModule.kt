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

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    companion object {

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): CryptoDatabase =
            Room.databaseBuilder(
                context,
                CryptoDatabase::class.java,
                CryptoDatabase.DATABASE_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        @Singleton
        @Provides
        fun provideBookDao(cryptoDatabase: CryptoDatabase) =
            cryptoDatabase.getBookDao()

        @Singleton
        @Provides
        fun provideAskDao(cryptoDatabase: CryptoDatabase) =
            cryptoDatabase.getAskDao()

        @Singleton
        @Provides
        fun provideBidsDao(cryptoDatabase: CryptoDatabase) =
            cryptoDatabase.getBidDao()

        @Singleton
        @Provides
        fun provideTickerDao(cryptoDatabase: CryptoDatabase) =
            cryptoDatabase.getTickerDao()
    }
}
