package com.example.capstone_project.data.network

import com.example.capstone_project.data.entities.response.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TickerApiClient {
    @GET("ticker/?")
    suspend fun getTicker(@Query("book") book: String): Response<TickerResponse>
}
