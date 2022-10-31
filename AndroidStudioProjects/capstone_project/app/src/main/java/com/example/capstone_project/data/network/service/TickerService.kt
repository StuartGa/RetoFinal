package com.example.capstone_project.data.network.service

import android.util.Log
import com.example.capstone_project.data.entities.model.Ticker
import com.example.capstone_project.data.entities.response.TickerResponse
import com.example.capstone_project.data.network.CriptoApiClient
import com.example.capstone_project.data.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TickerService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getTicker(book: String): TickerResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CriptoApiClient::class.java).getTicker(book)
            println("Ticker "+ response)
            response.body() ?: TickerResponse(false, ticker = Ticker("", "", " "," "))
        }
    }
}
