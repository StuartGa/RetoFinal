package com.example.capstone_project.data.network.service

import com.example.capstone_project.data.network.CriptoApiClient
import com.example.capstone_project.data.network.entities.model.BidsAskModel
import com.example.capstone_project.data.network.entities.model.Ticker
import com.example.capstone_project.data.network.entities.response.AvaibleBookResponse
import com.example.capstone_project.data.network.entities.response.OrderBookResponse
import com.example.capstone_project.data.network.entities.response.TickerResponse
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class CryptoService @Inject constructor(private val apiService: CriptoApiClient) {

    suspend fun getOrderBooks(): AvaibleBookResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAvaibleBooks()
            println("Response retro" + response)
            response.body() ?: AvaibleBookResponse(false, emptyList())
        }
    }
    suspend fun getOrderBooks(book: String): OrderBookResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.getOrderBook(book)
            println("response order book Api " + response)
            println("book" + book)
            response.body() ?: OrderBookResponse(false, BidsAskModel(emptyList(), emptyList()))
        }
    }

    suspend fun getTicker(book: String): TickerResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.getTicker(book)
            println("Ticker "+ response)
            response.body() ?: TickerResponse(false, ticker = Ticker("", "", " "," "))
        }
    }
}