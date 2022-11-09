package com.example.capstone_project.config

import com.example.capstone_project.data.network.CriptoApiClient
import com.example.capstone_project.data.network.CriptoEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofit() =
        Retrofit.Builder()
            .baseUrl(CriptoEndpoints.criptoUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): CriptoApiClient =
        retrofit.create(CriptoApiClient::class.java)
}
