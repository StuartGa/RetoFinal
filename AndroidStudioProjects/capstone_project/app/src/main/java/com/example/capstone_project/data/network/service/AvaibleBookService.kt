package com.example.capstone_project.data.network.service

import com.example.capstone_project.data.network.CriptoApiClient
import com.example.capstone_project.data.network.RetrofitHelper
import com.example.capstone_project.data.network.entities.response.AvaibleBookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AvaibleBookService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getOrderBooks(): AvaibleBookResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CriptoApiClient::class.java).getAvaibleBooks()
            println("Response retro" + response)
            response.body() ?: AvaibleBookResponse(false, emptyList())
            // OrderBookResponse(false, List<Ask>(0, { Ask("", "", "") }), List<Bid>(1) { Bid("", "", "") })
        }
    }
}
