package com.example.capstone_project.data.entities.response

import com.example.capstone_project.data.entities.model.Ticker
import com.google.gson.annotations.SerializedName

data class TickerResponse(
    @SerializedName("success") val succes: Boolean = true,
    @SerializedName("payload") val ticker: Ticker
)
