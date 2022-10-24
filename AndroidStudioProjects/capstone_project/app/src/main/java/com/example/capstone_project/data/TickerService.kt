package com.example.capstone_project.data

import android.util.Log
import com.example.capstone_project.data.network.RetrofitHelper
import com.example.capstone_project.data.network.TickerApiClient
import com.example.capstone_project.data.entities.model.Ticker
import com.example.capstone_project.data.entities.response.TickerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TickerService {
    private val retrofit = RetrofitHelper.getRetrofit("https://api.bitso.com/v3/")

    suspend fun getTicker(): TickerResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TickerApiClient::class.java).getTicker("btc_mxn")
            println("reponse" + response.body())
            Log.d("error", response.body().toString())
            response.body() ?: TickerResponse(false, ticker = Ticker("", "", ""))
        }
    }
}
