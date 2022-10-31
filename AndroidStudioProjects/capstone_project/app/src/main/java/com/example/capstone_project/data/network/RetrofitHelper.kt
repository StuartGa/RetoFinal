package com.example.capstone_project.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CriptoEndpoints.criptoUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
