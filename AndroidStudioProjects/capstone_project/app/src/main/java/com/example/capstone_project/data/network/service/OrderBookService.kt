package com.example.capstone_project.data.network.service


import com.example.capstone_project.data.network.entities.model.BidsAskModel
import com.example.capstone_project.data.network.entities.response.OrderBookResponse
import com.example.capstone_project.data.network.CriptoApiClient
import com.example.capstone_project.data.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderBookService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getOrderBooks(book: String): OrderBookResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CriptoApiClient::class.java).getOrderBook(book)
            println("response order book Api " + response)
            println("book" + book)
            response.body() ?: OrderBookResponse(false, BidsAskModel(emptyList(), emptyList()))
        }
    }
}
