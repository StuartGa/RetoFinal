package com.example.capstone_project.data.network.entities.model // ktlint-disable package-name

import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("book") val book: String,
    @SerializedName("last") val last: String,
    @SerializedName("high") val high: String,
    @SerializedName("low") val low: String
)
