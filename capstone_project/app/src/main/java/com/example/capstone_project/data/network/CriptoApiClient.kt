package com.example.capstone_project.data.network

import com.example.capstone_project.data.network.entities.response.AvaibleBookResponse
import com.example.capstone_project.data.network.entities.response.OrderBookResponse
import com.example.capstone_project.data.network.entities.response.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CriptoApiClient {
    @GET("ticker/")
    suspend fun getTicker(@Query("book") book: String): Response<TickerResponse>

    @GET("available_books/")
    suspend fun getAvaibleBooks(): Response<AvaibleBookResponse>

    @GET("order_book/")
    suspend fun getOrderBook(@Query("book") book: String ): Response<OrderBookResponse>
}
