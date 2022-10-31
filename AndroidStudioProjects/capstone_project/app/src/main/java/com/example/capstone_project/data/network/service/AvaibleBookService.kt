package com.example.capstone_project.data.network.service

import com.example.capstone_project.data.entities.model.Ask
import com.example.capstone_project.data.entities.model.Bid
import com.example.capstone_project.data.entities.model.Book
import com.example.capstone_project.data.entities.response.AvaibleBookResponse
import com.example.capstone_project.data.entities.response.OrderBookResponse
import com.example.capstone_project.data.network.CriptoApiClient
import com.example.capstone_project.data.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AvaibleBookService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getOrderBooks(): AvaibleBookResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CriptoApiClient::class.java).getAvaibleBooks()
            response.body() ?: AvaibleBookResponse(false, emptyList())
            //OrderBookResponse(false, List<Ask>(0, { Ask("", "", "") }), List<Bid>(1) { Bid("", "", "") })
        }
    }
}
