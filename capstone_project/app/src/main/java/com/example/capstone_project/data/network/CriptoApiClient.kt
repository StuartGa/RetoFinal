package com.example.capstoneproject.data.network

import com.example.capstoneproject.data.network.entities.response.AvaibleBookResponse
import com.example.capstoneproject.data.network.entities.response.OrderBookResponse
import com.example.capstoneproject.data.network.entities.response.TickerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CriptoApiClient {
    @GET("ticker/")
    fun getTicker(@Query("book") book: String): Single<TickerResponse>

    @GET("available_books/")
    suspend fun getAvaibleBooks(): Response<AvaibleBookResponse>

    @GET("order_book/")
    suspend fun getOrderBook(@Query("book") book: String): Response<OrderBookResponse>
}
