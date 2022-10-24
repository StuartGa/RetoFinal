package com.example.capstone_project.data

import com.example.capstone_project.data.entities.response.TickerResponse

class TickerRepository {
    private  val tickerApi = TickerService()

    suspend fun getTicker(): TickerResponse {
        val response = tickerApi.getTicker()
        //TickerProvider.ticker = response
        return response
    }
}