package com.example.capstoneproject.data.network.service

import com.example.capstoneproject.data.network.CriptoApiClient
import com.example.capstoneproject.data.network.entities.model.BidsAskModel
import com.example.capstoneproject.data.network.entities.response.AvaibleBookResponse
import com.example.capstoneproject.data.network.entities.response.OrderBookResponse
import com.example.capstoneproject.data.network.entities.response.TickerResponse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoService @Inject constructor(private val apiService: CriptoApiClient) {

    suspend fun getOrderBooks(): AvaibleBookResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAvaibleBooks()
            response.body() ?: AvaibleBookResponse(false, emptyList())
        }
    }
    suspend fun getOrderBooks(book: String): OrderBookResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.getOrderBook(book)
            response.body() ?: OrderBookResponse(false, BidsAskModel(emptyList(), emptyList()))
        }
    }

    fun getTicker(book: String): Single<TickerResponse> {
        println("Response ticker" + apiService.getTicker(book))
        return apiService.getTicker(book)
    }
}
